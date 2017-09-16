package web.action;

import util.ResultPage;
import util.base.action.BaseAction;
import web.pojo.HfwContacts;
import web.service.IRedpacketManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 红包领取action
 * Created by whli
 *
 * @version 2016/7/18 8:28
 */
public class RedpacketManageAction extends BaseAction{
    private IRedpacketManageService redpacketManageService;
    private HfwContacts q;
    private ResultPage rp;
    private Date currentDate;  //当前时间
    private String h_flag; //标识是否领取
    private String yzm;

    //查询所有领取人
    public String queryHFWContantsList(){
        try {
            rp = redpacketManageService.queryHfwContactsList(q,getPage(),getPageRows());
            super.setPageParam(rp);
            currentDate = new Date();
        }catch (Exception e){
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    //ajax保存领取人或更改领取状态 (1验证码不正确 2领取人重复领取 3领取成功)
    public void hfwContactsEdit(){
        try {
            //判断验证码是否正确
            if(yzm != null){
                if(!super.getRequest().getSession().getAttribute("RANDOMVALIDATECODEKEY").toString().equals(yzm)){
                    super.returnAjaxInfo("1");
                    return ;
                }
            }
            if(h_flag == null){
                List<String> params = new ArrayList<String>();
                params.add(q.getLinkPhone());
                params.add(q.getPacket().getOid());
                HfwContacts checkContanct = redpacketManageService.getHfwContanctsByPhoneAndPacket(params);
                if(checkContanct != null){
                    super.returnAjaxInfo("2");
                    return;
                }
            }
            redpacketManageService.saveOrUpdateHfwContacts(q,h_flag);
            super.returnAjaxInfo("3");
        }catch (Exception e){
            e.printStackTrace();
        }
//        return SUCCESS;
    }

    public IRedpacketManageService getRedpacketManageService() {
        return redpacketManageService;
    }

    public void setRedpacketManageService(IRedpacketManageService redpacketManageService) {
        this.redpacketManageService = redpacketManageService;
    }

    public HfwContacts getQ() {
        return q;
    }

    public void setQ(HfwContacts q) {
        this.q = q;
    }

    public ResultPage getRp() {
        return rp;
    }

    public void setRp(ResultPage rp) {
        this.rp = rp;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getH_flag() {
        return h_flag;
    }

    public void setH_flag(String h_flag) {
        this.h_flag = h_flag;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }
}
