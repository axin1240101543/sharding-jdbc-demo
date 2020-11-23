package com.darren.center.shardingjdbc.demo.hint;

/**
 * 表示三个不同的配置文件：
 * 数据库级别
 * 表级别
 * 读写分离
 */
public enum HintType {
    
    DATABASE_ONLY, DATABASE_TABLES, MASTER_ONLY
}