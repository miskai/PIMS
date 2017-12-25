<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>个人信息管理系统——注册页面</title>
    <style>
        body{
            padding-top: 5%;
            background: url("../images/zhu2.jpg") no-repeat;
            background-size:100% 1080px;
        }
    </style>
</head>
<body >
<table align="center">
    <tr>
        <td colspan="3" align="center">
            <h3>
                <font color="red">请填写以下信息</font>
            </h3>
        </td>
    </tr>
    <tr>
        <td>
            <form action="http://localhost:8080/RegisterServlet"
                  method="post">
                <table border="2" cellspacing="0" cellpadding="0" >
                    <tr>
                        <td>登录名字</td>
                        <td><input type="text" name="userName" size="20"/></td>
                    </tr>

                    <tr>
                        <td>用户密码</td>
                        <td><input type="password" name="password1" size="20">
                        </td>
                    </tr>

                    <tr>
                        <td>重复密码</td>
                        <td><input type="password" name="password2" size="20">
                        </td>
                    </tr>

                    <tr>
                        <td>用户姓名</td>
                        <td><input type="text" name="name" size="20"></td>
                    </tr>

                    <tr>
                        <td>用户性别</td>
                        <td><input type="radio" name="sex" value="男" checked>男
                            <input type="radio" name="sex" value="女" checked>女
                        </td>
                    </tr>

                    <tr>
                        <td>出生日期</td>
                        <td><select name="year" size="1">
                            <option value="1978">1978</option>
                            <option value="1979">1979</option>
                            <option value="1980">1980</option>
                            <option value="1981">1981</option>
                            <option value="1982">1982</option>
                            <option value="1983">1983</option>
                            <option value="1984">1984</option>
                            <option value="1985">1985</option>
                            <option value="1986">1986</option>
                            <option value="1987">1987</option>
                            <option value="1988">1988</option>
                            <option value="1989">1989</option>
                            <option value="1990">1990</option>
                            <option value="1991">1991</option>
                            <option value="1992">1992</option>
                            <option value="1993">1993</option>
                            <option value="1994">1994</option>
                            <option value="1995">1995</option>
                            <option value="1996">1996</option>
                            <option value="1997">1997</option>
                            <option value="1998">1998</option>
                        </select>年 <select name="month" size="1">
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>月 <select name="day" size="1">
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option>
                            <option value="23">23</option>
                            <option value="24">24</option>
                            <option value="25">25</option>
                            <option value="26">26</option>
                            <option value="27">27</option>
                            <option value="28">28</option>
                            <option value="29">29</option>
                            <option value="30">30</option>
                            <option value="31">31</option>
                        </select>日
                        </td>
                    </tr>

                    <td>用户民族</td>
                    <td><input type="radio" name="nation" value="汉族" checked>汉族
                        <input type="radio" name="nation" value="回族" checked>回族 <input
                                type="radio" name="nation" value="壮族" checked>壮族 <input
                                type="radio" name="nation" value="其他" checked>其他
                    </td>
                    </tr>
                    <tr>
                        <td>用户学历</td>
                        <td><select name="edu" size="1">
                            <option value="博士">博士</option>
                            <option value="硕士">硕士</option>
                            <option value="本科">本科</option>
                            <option value="专科">专科</option>
                            <option value="高中">高中</option>
                            <option value="初中">初中</option>
                            <option value="小学">小学</option>
                            <option value="其他">其他</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td>用户类型</td>
                        <td><select name="work" size="1">
                            <option value="软件开发工程师">软件开发工程师</option>
                            <option value="软件测试工程师">软件测试工程师</option>
                            <option value="教师">教师</option>
                            <option value="学生">学生</option>
                            <option value="经理">经理</option>
                            <option value="职员">职员</option>
                            <option value="老板">老板</option>
                            <option value="公务员">公务员</option>
                            <option value="其他">其他</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td>用户电话</td>
                        <td><input type="text" name="phone" size="20"/></td>
                    </tr>
                    <tr>
                        <td>家庭住址</td>
                        <td><select name="place" size="1">
                            <option value="北京">北京</option>
                            <option value="上海">上海</option>
                            <option value="天津">天津</option>
                            <option value="河北">河北</option>
                            <option value="河南">河南</option>
                            <option value="吉林">吉林</option>
                            <option value="黑龙江">黑龙江</option>
                            <option value="内蒙古">内蒙古</option>
                            <option value="山东">山东</option>
                            <option value="山西">山西</option>
                            <option value="陕西">陕西</option>
                            <option value="甘肃">甘肃</option>
                            <option value="宁夏">宁夏</option>
                            <option value="青海">青海</option>
                            <option value="新疆">新疆</option>
                            <option value="辽宁">辽宁</option>
                            <option value="江苏">江苏</option>
                            <option value="浙江">浙江</option>
                            <option value="安徽">安徽</option>
                            <option value="广东">广东</option>
                            <option value="海南">海南</option>
                            <option value="广西">广西</option>
                            <option value="云南">云南</option>
                            <option value="贵州">贵州</option>
                            <option value="四川">四川</option>
                            <option value="重庆">重庆</option>
                            <option value="西藏">西藏</option>
                            <option value="香港">香港</option>
                            <option value="澳门">澳门</option>
                            <option value="福建">福建</option>
                            <option value="江西">江西</option>
                            <option value="湖南">湖南</option>
                            <option value="青海">青海</option>
                            <option value="湖北">湖北</option>
                            <option value="台湾">台湾</option>
                            <option value="其他">其他</option>
                        </select>省
                        </td>
                    </tr>
                    <tr>
                        <td>邮箱地址</td>
                        <td><input type="text" name="email" size="20"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit"
                                                              value="确定" size="12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
                                type="reset" value="清除" size="12"></td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
</body>
</html>