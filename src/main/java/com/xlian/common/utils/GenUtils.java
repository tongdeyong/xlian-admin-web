//package com.xlian.common.utils;
//
//import com.xlian.system.model.Column;
//import com.xlian.system.model.Table;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.WordUtils;
//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.Velocity;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.*;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
///**
// * 代码生成器   工具类
// */
//public class GenUtils {
//
//
//    public static List<String> getTemplates() {
//        List<String> templates = new ArrayList<>();
//        templates.add("templates/generator/domain.java.vm");
//        templates.add("templates/generator/Dao.java.vm");
//        templates.add("templates/generator/Mapper.xml.vm");
//        templates.add("templates/generator/Service.java.vm");
//        templates.add("templates/generator/ServiceImpl.java.vm");
//        templates.add("templates/generator/Controller.java.vm");
//        return templates;
//    }
//
//    /**
//     * 生成代码
//     */
//
//
//    public static void generatorCode(Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip) {
//        //配置信息
//        //表信息
//        Table table1 = new Table();
//        table1.setTableName(table.get("tableName"));
//        table1.setTableComment(table.get("tableComment"));
//        //表名转换成Java类名
//        String className = tableToJava(table1.getTableName());
//        table.setClassName(className);
//        table.setClassname(StringUtils.uncapitalize(className));
//
//        //列信息
//        List<Column> columnList = new ArrayList<>();
//        for (Map<String, String> column : columns) {
//            Column columnDO = new Column();
//            columnDO.setColumnName(column.get("columnName"));
//            columnDO.setColumnType(column.get("columnType"));
//            columnDO.setColumnComment(column.get("columnComment"));
//
//            //列名转换成Java属性名
//            String attrName = columnToJava(columnDO.getColumnName());
//            columnDO.setAttrName(attrName);
//            columnDO.setAttrname(StringUtils.uncapitalize(attrName));
//
//            //列的数据类型，转换成Java类型
//            String attrType = config.getString(columnDO.getDataType(), "unknowType");
//            columnDO.setAttrType(attrType);
//
//            //是否主键
//            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableDO.getPk() == null) {
//                tableDO.setPk(columnDO);
//            }
//
//            columsList.add(columnDO);
//        }
//        tableDO.setColumns(columsList);
//
//        //没主键，则第一个字段为主键
//        if (tableDO.getPk() == null) {
//            tableDO.setPk(tableDO.getColumns().get(0));
//        }
//
//        //设置velocity资源加载器
//        Properties prop = new Properties();
//        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//        Velocity.init(prop);
//
//        //封装模板数据
//        Map<String, Object> map = new HashMap<>(16);
//        map.put("tableName", tableDO.getTableName());
//        map.put("comments", tableDO.getComments());
//        map.put("pk", tableDO.getPk());
//        map.put("className", tableDO.getClassName());
//        map.put("classname", tableDO.getClassname());
//        map.put("pathName", config.getString("package").substring(config.getString("package").lastIndexOf(".") + 1));
//        map.put("columns", tableDO.getColumns());
//        map.put("package", config.getString("package"));
//        map.put("author", config.getString("author"));
//        map.put("email", config.getString("email"));
//        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
//        VelocityContext context = new VelocityContext(map);
//
//        //获取模板列表
//        List<String> templates = getTemplates();
//        for (String template : templates) {
//            //渲染模板
//            StringWriter sw = new StringWriter();
//            Template tpl = Velocity.getTemplate(template, "UTF-8");
//            tpl.merge(context, sw);
//
//            try {
//                //添加到zip
//                zip.putNextEntry(new ZipEntry(getFileName(template, tableDO.getClassname(), tableDO.getClassName(), config.getString("package").substring(config.getString("package").lastIndexOf(".") + 1))));
//                IOUtils.write(sw.toString(), zip, "UTF-8");
//                IOUtils.closeQuietly(sw);
//                zip.closeEntry();
//            } catch (IOException e) {
//                throw new BDException("渲染模板失败，表名：" + tableDO.getTableName(), e);
//            }
//        }
//    }
//
//
//    /**
//     * 列名转换成Java属性名
//     */
//    public static String columnToJava(String columnName) {
//        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
//    }
//
//    /**
//     * 表名转换成Java类名
//     */
//    public static String tableToJava(String tableName) {
//
//        return columnToJava(tableName);
//    }
//
//    /**
//     * 获取配置信息
//     */
//    public static Configuration getConfig() {
//        try {
//            return new PropertiesConfiguration("generator.properties");
//        } catch (ConfigurationException e) {
//            throw new BDException("获取配置文件失败，", e);
//        }
//    }
//
//    /**
//     * 获取文件名
//     */
//    public static String getFileName(String template, String classname, String className, String packageName) {
//        String packagePath = "main" + File.separator + "java" + File.separator;
//        //String modulesname=config.getString("packageName");
//        if (StringUtils.isNotBlank(packageName)) {
//            packagePath += packageName.replace(".", File.separator) + File.separator;
//        }
//
//        if (template.contains("domain.java.vm")) {
//            return packagePath + "domain" + File.separator + className + "DO.java";
//        }
//
//        if (template.contains("Dao.java.vm")) {
//            return packagePath + "dao" + File.separator + className + "Dao.java";
//        }
//
//
//        if (template.contains("Service.java.vm")) {
//            return packagePath + "service" + File.separator + className + "Service.java";
//        }
//
//        if (template.contains("ServiceImpl.java.vm")) {
//            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
//        }
//
//        if (template.contains("Controller.java.vm")) {
//            return packagePath + "controller" + File.separator + className + "Controller.java";
//        }
//
//        if (template.contains("Mapper.xml.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + packageName + File.separator + className + "Mapper.xml";
//        }
//
//        if (template.contains("list.html.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
//                    + packageName + File.separator + classname + File.separator + classname + ".html";
//            //				+ "modules" + File.separator + "generator" + File.separator + className.toLowerCase() + ".html";
//        }
//        if (template.contains("add.html.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
//                    + packageName + File.separator + classname + File.separator + "add.html";
//        }
//        if (template.contains("edit.html.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
//                    + packageName + File.separator + classname + File.separator + "edit.html";
//        }
//
//        if (template.contains("list.js.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
//                    + "appjs" + File.separator + packageName + File.separator + classname + File.separator + classname + ".js";
//            //		+ "modules" + File.separator + "generator" + File.separator + className.toLowerCase() + ".js";
//        }
//        if (template.contains("add.js.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
//                    + "appjs" + File.separator + packageName + File.separator + classname + File.separator + "add.js";
//        }
//        if (template.contains("edit.js.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
//                    + "appjs" + File.separator + packageName + File.separator + classname + File.separator + "edit.js";
//        }
//
//
//        return null;
//    }
//}
