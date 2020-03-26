package com.kidd.demos.webservice.springws;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.SourceExtractor;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * @author Kidd
 */
public class SpringWsClient {

    private static final String WSDL_URL = "http://58.60.229.60:8002/SPD_DDI/DDIService.asmx?wsdl";

//    private WebServiceTemplate webServiceTemplate;

    public static void main(String[] args){
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri(WSDL_URL);
        StringSource stringSource = new StringSource("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:sendSaleBillList>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:username>test</tem:username>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:password>test</tem:password>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:saleBillLists>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <tem:SaleBillBean>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:hospital_code>1</tem:hospital_code>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:salebillid>1</tem:salebillid>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:barcode>1</tem:barcode>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:OUT_ORDER_NO>1</tem:OUT_ORDER_NO>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:ven_goods>1</tem:ven_goods>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:ven_batch_nbr>1</tem:ven_batch_nbr>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:address_code>1</tem:address_code>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:salebillno>1</tem:salebillno>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:ven_goodsname>1</tem:ven_goodsname>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:ven_spec>1</tem:ven_spec>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:ven_producer>1</tem:ven_producer>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:ven_unit>1</tem:ven_unit>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:prddate>1</tem:prddate>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:enddate>1</tem:enddate>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:msunitno>1</tem:msunitno>\n" +
                "               <tem:packnum>1</tem:packnum>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:ratifier>?</tem:ratifier>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:inv_type>1</tem:inv_type>\n" +
                "               <tem:billamt>1</tem:billamt>\n" +
                "               <tem:billqty>1</tem:billqty>\n" +
                "               <tem:price>1</tem:price>\n" +
                "               <tem:prc>1</tem:prc>\n" +
                "               <tem:taxrate>1</tem:taxrate>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:tax>1</tem:tax>\n" +
                "               <tem:amt>1</tem:amt>\n" +
                "               <tem:sumvalue>1</tem:sumvalue>\n" +
                "               <tem:trdprc>1</tem:trdprc>\n" +
                "               <tem:rtlprc>1</tem:rtlprc>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:mark1>?</tem:mark1>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:mark2>1</tem:mark2>\n" +
                "               <tem:mark3>1</tem:mark3>\n" +
                "               <tem:mark4>1</tem:mark4>\n" +
                "               <!--Optional:-->\n" +
                "               <tem:mark5>1</tem:mark5>\n" +
                "            </tem:SaleBillBean>\n" +
                "         </tem:saleBillLists>\n" +
                "      </tem:sendSaleBillList>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        StringResult stringResult = new StringResult();
        webServiceTemplate.sendSourceAndReceiveToResult(stringSource, stringResult);
        System.out.println(stringResult.toString());
    }
}
