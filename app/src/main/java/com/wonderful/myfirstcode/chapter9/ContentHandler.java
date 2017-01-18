package com.wonderful.myfirstcode.chapter9;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Function：SAX 解析 handler
 * Author：kxwon on 2017/1/17 23:48
 * Email：kxwonder@163.com
 */

public class ContentHandler extends DefaultHandler {

    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder sex;

    /**
     * 开始 XML 解析时调用
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        sex = new StringBuilder();
    }

    /**
     * 开始解析某个节点时调用
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 记录当前节点名
        nodeName = localName;
    }

    /**
     * 获取节点中的内容时调用
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 根据当前节点名判断将内容添加到哪一个 StringBuilder 对象中
        if ("id".equals(nodeName)){
            id.append(ch,start,length);
        }else if ("name".equals(nodeName)){
            name.append(ch,start,length);
        }else if ("sex".equals(nodeName)){
            sex.append(ch,start,length);
        }
    }

    /**
     * 完成解析某个节点时调用
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("student".equals(localName)){
            Log.d("sax解析：", "id is" + id.toString().trim());
            Log.d("sax解析：", "name is" + name.toString().trim());
            Log.d("sax解析：", "sex is" + sex.toString().trim());
            // 最后要将 StringBuilder 清空掉
            id.setLength(0);
            name.setLength(0);
            sex.setLength(0);
        }
    }


    /**
     * 完成整个 XML 解析时调用
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
