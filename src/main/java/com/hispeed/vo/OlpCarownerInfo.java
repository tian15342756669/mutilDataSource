package com.hispeed.vo;

import java.util.Date;

/**
 * Created by wangdf on 2017/6/27.
 * 新用户
 */
public class OlpCarownerInfo {
    //逻辑主键
    private long carOwnerId;
    //电话
    private String carOwnerMobile;
    //姓名
    private String carOwnerName;
    //昵称
    private String carOwnerNickname;
    //性别 0男士 1女士 -1不男不女
    private int carOwnerSex;
    //身份证号码
    private String carOwnerIdCardNo;
    //车牌号
    private String carOwnerPlateNumber;
    //集团ID
    private long blockId;
    //集团会员级别
    private long blockMemberId;
    //用户余额
    private long accountAmount;
    //用户积分
    private long points;
    //用户头像路径
    private String headPortrait;
    //生日
    private String birthDay;
    //注册时间
    private Date regTime;
    //车主状态
    private int carOwnerStatus;


    //-------------
    private int memberLevel;//会员级别，1级为最低，越大级别越高
    private String memberName;//会员级别名称
    private String carOwnerSexDesc;//
    //邮箱地址
    private String emailAddress;

    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCarOwnerSexDesc() {
        return carOwnerSexDesc;
    }

    public void setCarOwnerSexDesc(String carOwnerSexDesc) {
        this.carOwnerSexDesc = carOwnerSexDesc;
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public long getCarOwnerId() {
        return carOwnerId;
    }

    public void setCarOwnerId(long carOwnerId) {
        this.carOwnerId = carOwnerId;
    }

    public String getCarOwnerMobile() {
        return carOwnerMobile;
    }

    public void setCarOwnerMobile(String carOwnerMobile) {
        this.carOwnerMobile = carOwnerMobile;
    }

    public String getCarOwnerName() {
        return carOwnerName;
    }

    public void setCarOwnerName(String carOwnerName) {
        this.carOwnerName = carOwnerName;
    }

    public String getCarOwnerNickname() {
        return carOwnerNickname;
    }

    public void setCarOwnerNickname(String carOwnerNickname) {
        this.carOwnerNickname = carOwnerNickname;
    }

    public int getCarOwnerSex() {
        return carOwnerSex;
    }

    public void setCarOwnerSex(int carOwnerSex) {
        this.carOwnerSex = carOwnerSex;
    }

    public String getCarOwnerIdCardNo() {
        return carOwnerIdCardNo;
    }

    public void setCarOwnerIdCardNo(String carOwnerIdCardNo) {
        this.carOwnerIdCardNo = carOwnerIdCardNo;
    }

    public String getCarOwnerPlateNumber() {
        return carOwnerPlateNumber;
    }

    public void setCarOwnerPlateNumber(String carOwnerPlateNumber) {
        this.carOwnerPlateNumber = carOwnerPlateNumber;
    }

    public long getBlockId() {
        return blockId;
    }

    public void setBlockId(long blockId) {
        this.blockId = blockId;
    }

    public long getBlockMemberId() {
        return blockMemberId;
    }

    public void setBlockMemberId(long blockMemberId) {
        this.blockMemberId = blockMemberId;
    }

    public long getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(long accountAmount) {
        this.accountAmount = accountAmount;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public int getCarOwnerStatus() {
        return carOwnerStatus;
    }

    public void setCarOwnerStatus(int carOwnerStatus) {
        this.carOwnerStatus = carOwnerStatus;
    }
}
