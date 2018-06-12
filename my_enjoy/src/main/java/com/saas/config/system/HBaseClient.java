package com.saas.config.system;


import com.alibaba.fastjson.JSONObject;
import com.saas.utils.collection.CollectionUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Auth: dingpengfei
 * Date: 2017/10/10 16:57
 * Title: HBASE 客户端
 * Describe: 连接hbase客户端
 **/
@Component
//@Lazy
@PropertySource(value = "classpath:resource/sys.properties")
public class HBaseClient {
    private static Logger logger = LoggerFactory.getLogger(HBaseClient.class);

    @Value("${hbase.ip}")
    private String ip;
    @Value("${hbase.port}")
    private String port;
    // 声明静态配置
    private  Configuration conf = null;
    private  Connection connection = null;

//    public HBaseClient() {
//        connection = buildConnection();
//    }

    {

    }

    /**
     * Auth: dingpengfei
     * Date: 2017/10/11 10:07
     * Param: 无
     * Return: hbase连接
     **/
    private  Connection buildConnection() {
        conf = HBaseConfiguration.create();
        conf.set(HConstants.ZOOKEEPER_QUORUM, ip);
        conf.set(HConstants.ZOOKEEPER_CLIENT_PORT, port);
        conf.set(HConstants.HBASE_CLIENT_PAUSE, "50");//失败重试时等待时间
        conf.set(HConstants.HBASE_CLIENT_RETRIES_NUMBER, "3");//失败时重试次数
        conf.set(HConstants.HBASE_CLIENT_RETRIES_NUMBER, "2000");//一次RPC请求的超时时间
        conf.set(HConstants.HBASE_CLIENT_OPERATION_TIMEOUT, "3000");//HBase客户端发起一次数据操作直至得到响应之间总的超时时间
        conf.set(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD, "10000");//HBase客户端发起一次scan操作的rpc调用至得到响应之间总的超时时间
        try {
            return getConnect(conf);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Auth: dingpengfei
     * Date: 2017/10/11 10:11
     * Param: 获得HBase连接
     * Return: HBase连接
     **/
    private  Connection getConnect(Configuration conf) throws IOException {
        if (conf == null) {
            return buildConnection();
        }
        if (connection == null || connection.isClosed()) {
            connection = ConnectionFactory.createConnection(conf);
        }
        return connection;
    }

//    /**
//     * Auth: dingpengfei
//     * Date: 2017/10/11 10:13
//     * Title: 查询hBase table
//     * Param: tableName  表名
//     * Return: table
//     **/
//    public  Table getHBaseTable(String tableName) throws Exception {
//        return getConnect(conf).getTable(TableName.valueOf(tableName));
//    }

    /**
     * Auth: dingpengfei
     * Date: 2017/10/11 10:13
     * Title: 查询hBase table 是否存在
     * Param: tableName  表名
     * Return: true/false
     **/
    private   boolean isExist(String tableName) throws IOException {
        Connection conn = getConnect(conf);
        HBaseAdmin hAdmin = (HBaseAdmin) conn.getAdmin();// 新建一个数据库管理员
        boolean flag = hAdmin.tableExists(tableName);
        hAdmin.close();
        return flag;
    }

    /**
     * Auth: dingpengfei
     * Date: 2017/10/11 10:15
     * * Title: 创建表
     * Param:  tableName        表名
     * Param:  columnFamilies   列族
     * Return: int              创建成功、失败
     **/
    private   int createTable(String tableName, String[] columnFamilies)
            throws Exception {
        Connection conn = getConnect(conf);
        // 新建一个数据库管理员
        HBaseAdmin hAdmin = (HBaseAdmin) conn.getAdmin();
        int flag = 0;
        if (hAdmin.tableExists(tableName)) {
            logger.info("表 " + tableName + " 已存在！");
            flag = 1;
        } else {
            // 新建一个表的描述

            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
            // 在描述里添加列族
            for (String columnFamily : columnFamilies) {
                tableDesc.addFamily(new HColumnDescriptor(columnFamily));
            }
            // 根据配置好的描述建表
            hAdmin.createTable(tableDesc);
            logger.info("创建表 " + tableName + " 成功!");
        }
        hAdmin.close();
        return flag;
    }

    public  int deleteTable(String tableName)
            throws Exception {
        Connection conn = getConnect(conf);
        // 新建一个数据库管理员
        HBaseAdmin hAdmin = (HBaseAdmin) conn.getAdmin();
        int flag = 0;
        if (hAdmin.tableExists(tableName)) {
            hAdmin.disableTable(tableName);
            hAdmin.deleteTable(tableName);
            logger.info("删除表 " + tableName + " 成功!");
            flag = 1;
        } else {
            logger.info("删除表 " + tableName + " 失败【不存在】!");
        }
        hAdmin.close();
        return flag;
    }
    /**
     * Auth: dingpengfei
     * Date: 2017/10/11 10:17
     * Title: 添加多行数据
     * Param: tableName     表名
     * Param: listPut       添加数据
     * Return: void
     **/
    public  void addMultiRows( List<Put> listPut,String tableName,String... columnFamilies) throws Exception {
        if (CollectionUtils.isEmpty(listPut)) {
            return;
        }
        Connection conn = getConnect(conf);
        if(!isExist(tableName)){
            createTable(tableName,columnFamilies);
        }
        Table table = conn.getTable(TableName.valueOf(tableName));
        if(CollectionUtil.isEmpty(listPut)){
            throw new Exception("数据集合为空");
        }
        if(listPut.size()>5000){
            List<List<Put>> list=CollectionUtil.splitList(listPut,5000);
//            listPut.clear();
            for(List<Put> puts:list){
                logger.info(String.format("向hbase table 【%s】写入数据 data 【%s】",tableName,puts));
                table.put(puts);
//                list.remove(puts);
            }
//            list.clear();
        }else {
            logger.info(String.format("向hbase table 【%s】写入数据 data 【%s】",tableName,listPut));
            table.put(listPut);
//            listPut.clear();
        }
        table.close();

    }

    /**
     * Auth: dingpengfei
     * Date: 2017/10/11 10:17
     * Title: 添加数据
     * Param: tableName     表名
     * Param: put       添加数据
     * Return: void
     **/
    public  void addRow(Put put,String tableName,String... columnFamilies ) throws Exception {
        if (put == null || put.size() < 1) {
            return;
        }
        Connection conn = getConnect(conf);
        if(!isExist(tableName)){
            createTable(tableName,columnFamilies);
        }
        Table table = conn.getTable(TableName.valueOf(tableName));
        table.put(put);
        table.close();
    }

    /**
     * Auth: dingpengfei
     * Date: 2017/10/11 10:19
     * Title: 获得单行数据
     * Param: tableName 表名
     * Param: row       rowkey
     * Return: map
     **/
    @SuppressWarnings("deprecation")
    public  Map<String, String> getRow(String tableName, String row) throws Exception {
        Connection conn = getConnect(conf);
        Table table = conn.getTable(TableName.valueOf(tableName));
        Map<String, String> map = new HashMap<>();
        if (table != null) {
            Get get = new Get(Bytes.toBytes(row));
            get.setConsistency(Consistency.STRONG);
            Result result = table.get(get);
            for (KeyValue rowKV : result.raw()) {
                String fieldName = new String(rowKV.getQualifier());
                String value = new String(rowKV.getValue());
                map.put(fieldName, value);
            }
            table.close();
        }

        return map;
    }

    /**
     * Auth: dingpengfei
     * Date: 2017/10/11 10:21
     * Title: 关闭连接
     **/
    public  void close() {
        if (connection != null && !connection.isClosed()) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public  List<Map<String,String>> selectByFilter(String tableName,List<String> arr) throws IOException{
        Connection conn = getConnect(conf);
        Table table = conn.getTable(TableName.valueOf(tableName));
        FilterList filterList = new FilterList();
        Scan s1 = new Scan();
        if(!CollectionUtil.isEmpty(arr)){
            for(String v:arr){ // 各个条件之间是“与”的关系
                String [] s=v.split(",");
                filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(s[0]),
                                Bytes.toBytes(s[1]),
                                CompareFilter.CompareOp.EQUAL,Bytes.toBytes(s[2])
                        )
                );
            }
            s1.setFilter(filterList);
        }

        ResultScanner results = table.getScanner(s1);
        List<Map<String,String>> list=new ArrayList<>();
        for(Result rr=results.next();rr!=null;rr=results.next()){
            Map<String,String> map=new HashMap<>();
            for(Cell kv:rr.listCells()){



                map.put(new String(kv.getQualifier(),"UTF-8"),
                        new String(kv.getValue(),"UTF-8"));

            }
            list.add(map);
        }

        return CollectionUtil.isEmpty(list)?null:list;
    }


}
