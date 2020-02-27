package com.example.yandexnewsparser;

import android.util.Log;
import android.widget.ArrayAdapter;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ParsingClass extends DefaultHandler {
    private String URL = "https://news.yandex.ru/auto.rss";

    private ArrayList<PostValue> items =  new ArrayList<>();
    private PostValue item = null;
    public String currTagVal = "";
    boolean currTag = false;

    public ArrayList<PostValue> getPostsList(){
        return this.items;
    }
    public void get(){
        try{
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(this);
            InputStream inputStream = new URL(URL).openStream();
            xmlReader.parse(new InputSource(inputStream));
            Log.i("Yeah b", String.valueOf(URL));

        }catch (Exception e ){
            e.getMessage();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if(qName.equalsIgnoreCase("item")){
            currTag = true;
            item = new PostValue();
        }
        currTagVal = "";
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(localName.equalsIgnoreCase("item") && currTag){
            items.add(item);
        } else if (localName.equalsIgnoreCase("title") && currTag){
            item.setTitle(currTagVal);
        } else if(localName.equalsIgnoreCase("pubDate") && currTag){
            item.setDate(currTagVal);
        } else if(localName.equalsIgnoreCase("description") && currTag){
            item.setDescr(currTagVal);
        } else if (localName.equalsIgnoreCase("link") && currTag) {
            item.setLink(currTagVal);
        }
        currTagVal = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        currTagVal += new String(ch,start,length);
    }
}
