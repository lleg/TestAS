//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.10.17 at 09:26:02 PM MSK 
//


package ru.digitalspirit.asaka.microzime.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ��������� ���������� ���������
 * 
 * <p>Java class for AccountType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="isRosbank" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="UseBrokerAccount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Currency" type="{http://NFOCommon.NFO.ru}CurrencyType" minOccurs="0"/>
 *         &lt;element name="BeneficiaryBankBIC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BeneficiaryBankSWIFT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BeneficiaryBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BeneficiaryBankINN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BeneficiaryBankKPP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrespondentAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BeneficiaryBankCA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrespondentBankBIC" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountType", propOrder = {
    "id",
    "isRosbank",
    "useBrokerAccount",
    "number",
    "currency",
    "beneficiaryBankBIC",
    "beneficiaryBankSWIFT",
    "beneficiaryBankName",
    "beneficiaryBankINN",
    "beneficiaryBankKPP",
    "correspondentAccount",
    "beneficiaryBankCA",
    "correspondentBankBIC"
})
public class AccountType {

    protected BigInteger id;
    protected Boolean isRosbank;
    @XmlElement(name = "UseBrokerAccount")
    protected Boolean useBrokerAccount;
    @XmlElement(name = "Number")
    protected String number;
    @XmlElement(name = "Currency")
    protected CurrencyType currency;
    @XmlElement(name = "BeneficiaryBankBIC")
    protected String beneficiaryBankBIC;
    @XmlElement(name = "BeneficiaryBankSWIFT")
    protected String beneficiaryBankSWIFT;
    @XmlElement(name = "BeneficiaryBankName")
    protected String beneficiaryBankName;
    @XmlElement(name = "BeneficiaryBankINN")
    protected String beneficiaryBankINN;
    @XmlElement(name = "BeneficiaryBankKPP")
    protected String beneficiaryBankKPP;
    @XmlElement(name = "CorrespondentAccount")
    protected String correspondentAccount;
    @XmlElement(name = "BeneficiaryBankCA")
    protected String beneficiaryBankCA;
    @XmlElement(name = "CorrespondentBankBIC")
    protected List<String> correspondentBankBIC;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the isRosbank property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIsRosbank() {
        return isRosbank;
    }

    /**
     * Sets the value of the isRosbank property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRosbank(Boolean value) {
        this.isRosbank = value;
    }

    /**
     * Gets the value of the useBrokerAccount property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getUseBrokerAccount() {
        return useBrokerAccount;
    }

    /**
     * Sets the value of the useBrokerAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseBrokerAccount(Boolean value) {
        this.useBrokerAccount = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyType }
     *     
     */
    public CurrencyType getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyType }
     *     
     */
    public void setCurrency(CurrencyType value) {
        this.currency = value;
    }

    /**
     * Gets the value of the beneficiaryBankBIC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryBankBIC() {
        return beneficiaryBankBIC;
    }

    /**
     * Sets the value of the beneficiaryBankBIC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryBankBIC(String value) {
        this.beneficiaryBankBIC = value;
    }

    /**
     * Gets the value of the beneficiaryBankSWIFT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryBankSWIFT() {
        return beneficiaryBankSWIFT;
    }

    /**
     * Sets the value of the beneficiaryBankSWIFT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryBankSWIFT(String value) {
        this.beneficiaryBankSWIFT = value;
    }

    /**
     * Gets the value of the beneficiaryBankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryBankName() {
        return beneficiaryBankName;
    }

    /**
     * Sets the value of the beneficiaryBankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryBankName(String value) {
        this.beneficiaryBankName = value;
    }

    /**
     * Gets the value of the beneficiaryBankINN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryBankINN() {
        return beneficiaryBankINN;
    }

    /**
     * Sets the value of the beneficiaryBankINN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryBankINN(String value) {
        this.beneficiaryBankINN = value;
    }

    /**
     * Gets the value of the beneficiaryBankKPP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryBankKPP() {
        return beneficiaryBankKPP;
    }

    /**
     * Sets the value of the beneficiaryBankKPP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryBankKPP(String value) {
        this.beneficiaryBankKPP = value;
    }

    /**
     * Gets the value of the correspondentAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    /**
     * Sets the value of the correspondentAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrespondentAccount(String value) {
        this.correspondentAccount = value;
    }

    /**
     * Gets the value of the beneficiaryBankCA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryBankCA() {
        return beneficiaryBankCA;
    }

    /**
     * Sets the value of the beneficiaryBankCA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryBankCA(String value) {
        this.beneficiaryBankCA = value;
    }

    /**
     * Gets the value of the correspondentBankBIC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the correspondentBankBIC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCorrespondentBankBIC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCorrespondentBankBIC() {
        if (correspondentBankBIC == null) {
            correspondentBankBIC = new ArrayList<String>();
        }
        return this.correspondentBankBIC;
    }

    public void setCorrespondentBankBIC(List<String> value) {
        this.correspondentBankBIC = value;
    }

}
