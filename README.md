# TextInputLayoutDemo
这里展示Android Design Support Library 里的 TextInputLayout的炫酷用法,菜鸟学习用....
# 自定义弹出的错误提示的位置
    private boolean isPwdValid() {
        String pwdStr = et_pwd.getText().toString().trim();
        if ("".equals(pwdStr) || TextUtils.isEmpty(pwdStr)) {
            layout_pwd.setErrorEnabled(true);
            //如果给EditText设置setError时,弹出的错误提示是在输入框的右侧,以PopupWindow的形式,上对齐et的底部弹出
            //et_pwd.setError(getResources().getString(R.string.error_pwd)); 

            //如果给TextInputLayout设置setError时,弹出的错误提示是在整个TextInputLayout布局的左下角,直接显示文本的
            layout_pwd.setError(getResources().getString(R.string.error_pwd));
            et_pwd.requestFocus();
            return false;
        }
        layout_pwd.setErrorEnabled(false);
        return true;
    }
# 及时关闭错误提示
  layout_name.setErrorEnabled(false); //关闭错误提示(记得及时关闭,否则会一直出现)
  
# 是否添加 counterOverflowTextAppearance  自定义样式
    在布局文件中,TextInputLayout下不添加counterOverflowTextAppearance样式时,
        <android.support.design.widget.TextInputLayout
            android:id="@+id/layout_name"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:counterEnabled="true"
            app:counterMaxLength="5"
            app:counterOverflowTextAppearance="@style/MyOverflowText"
        >
     当单独设置app:counterEnabled="true"属性时,我们输入的的EditText的右下角会出现一个计数器,开始会出现一个0,随着输入字符的增多而逐步递增;
     当app:counterEnabled="true"和app:counterMaxLength="5" 同时设置时,同时还会出现一个最大输入字符数的显示;
     
     a.如果不添加app:counterOverflowTextAppearance属性
          当输入框输入文字超限(超过我们设置的app:counterMaxLength="5")时,提示文字和横线默认大红色(就是TextInputLayout的整个布局)
     这个不知道在哪里设置;
          不超限时,TextInputLayout的整个布局的颜色是在<item name="colorAccent">@color/colorAccent</item>中设置的--系统颜色;
          为空时:提示文字为系统颜色(colorAccent,同上),横线和警告文字为大红色;
          
     b.当添加app:counterOverflowTextAppearance属性
          输入文字框超限,TextInputLayout的整体样式为我们自定义的颜色;
          为空时:无论获取焦点与否,错误提示文字颜色显示默认的大红色,可修改;
          不超限:TextInputLayout的整体样式颜色为系统颜色 <item name="colorAccent">@color/colorAccent</item>,可修改
         
# 自定义错误信息的文字颜色
    分2步完成:
    a.也需要我们在styles.xml文件中写一个样式,我直接把我的样式粘过来了:
    <style name="MyErrorStyle" >
        <item name="android:textColor" >#FFFF00</item>
    </style>
    b.在布局文件中 app:errorTextAppearance="@style/MyErrorStyle"引用即可
    建议:默认的 错误提示文字 的颜色为大红色,很醒目,不建议修改.
 
# 小总结

    app:counterOverflowTextAppearance属性就是当我们输入字符超过限定个数的时候,TextInputLayout整体显示的颜色,我们可以自定义;
    正常及不超限时TextInputLayout的样式,也即是默认的样式颜色是在 <item name="colorAccent">@color/colorAccent</item> 中显示的,可以自行修改字体颜色;
    输入框为空时的错误提示文字的颜色,默认是大红色,可以自行修改;
# 注意
    当我们在Android Studio中导入support design的开发包时候,版本号最好和v7的版本号一致,否则有时候会出现莫名其妙的错误,示例如下:
    compile 'com.android.support:appcompat-v7:25.0.0'
    compile 'com.android.support:design:25.0.0'
