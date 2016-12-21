package cn.qqtheme.androidpicker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Locale;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.ColorPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.FilePicker;
import cn.qqtheme.framework.picker.LinkagePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import cn.qqtheme.framework.util.DateUtils;
import cn.qqtheme.framework.util.StorageUtils;
import cn.qqtheme.framework.widget.WheelView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onNestView(View view) {
        startActivity(new Intent(this, NestActivity.class));
    }

    public void onAnimationStyle(View view) {
        NumberPicker picker = new NumberPicker(this);
        picker.setAnimationStyle(R.style.Animation_CustomPopup);
        picker.setOffset(2);//偏移量
        picker.setRange(10.5, 20, 1.5);//数字范围
        picker.setSelectedItem(18.0);
        picker.setLabel("℃");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                showToast("index=" + index + ", item=" + item.doubleValue());
            }
        });
        picker.show();
    }

    public void onAnimator(View view) {
        CustomHeaderAndFooterPicker picker = new CustomHeaderAndFooterPicker(this);
        picker.setOffset(1);
        picker.setGravity(Gravity.CENTER);//居中
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                showToast("index=" + position + ", item=" + option);
            }
        });
        picker.show();
    }

    public void onYearMonthDayPicker(View view) {
        final DatePicker picker = new DatePicker(this);
        picker.setRangeStart(2016, 8, 29);
        picker.setRangeEnd(2111, 1, 11);
        picker.setSelectedItem(2050, 10, 14);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                showToast(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }


    public void onYearMonthDayTimePicker(View view) {
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_OF_DAY);
        picker.setRange(2000, 2030);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                showToast(year + "-" + month + "-" + day + " " + hour + ":" + minute);
            }
        });
        picker.show();
    }


    public void onYearMonthPicker(View view) {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
        picker.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        picker.setWidth((int) (picker.getScreenWidthPixels() * 0.6));
        picker.setRangeStart(2016, 10, 14);
        picker.setRangeEnd(2020, 11, 11);
        picker.setSelectedItem(2019, 9);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                showToast(year + "-" + month);
            }
        });
        picker.show();
    }

    public void onMonthDayPicker(View view) {
        DatePicker picker = new DatePicker(this, DatePicker.MONTH_DAY);
        picker.setGravity(Gravity.CENTER);//弹框居中
        picker.setRangeStart(5, 1);
        picker.setRangeEnd(12, 31);
        picker.setSelectedItem(10, 14);
        picker.setOnDatePickListener(new DatePicker.OnMonthDayPickListener() {
            @Override
            public void onDatePicked(String month, String day) {
                showToast(month + "-" + day);
            }
        });
        picker.show();
    }

    public void onTimePicker(View view) {
        TimePicker picker = new TimePicker(this, TimePicker.HOUR_24);
        picker.setRangeStart(9, 0);//09:00
        picker.setRangeEnd(18, 0);//18:30
        picker.setTopLineVisible(false);
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                showToast(hour + ":" + minute);
            }
        });
        picker.show();
    }

    public void onOptionPicker(View view) {
        OptionPicker picker = new OptionPicker(this, new String[]{
                "第一项", "第二项", "这是一个很长很长很长很长很长很长很长很长很长的很长很长的很长很长的项"
        });
        picker.setOffset(3);
        picker.setSelectedIndex(0);
        picker.setTextSize(11);
        picker.setLineConfig(new WheelView.LineConfig(0));//使用最长的线
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                showToast("index=" + index + ", item=" + item);
            }
        });
        picker.show();
    }

    public void onLinkagePicker(View view) {
        ArrayList<String> firstList = new ArrayList<String>();
        firstList.add("12小时制");
        firstList.add("24小时制");
        ArrayList<ArrayList<String>> secondList = new ArrayList<ArrayList<String>>();
        ArrayList<String> secondListItem1 = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            secondListItem1.add(DateUtils.fillZero(i) + "点");
        }
        ArrayList<String> secondListItem2 = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            secondListItem2.add(DateUtils.fillZero(i) + "点");
        }
        secondList.add(secondListItem1);//对应今天
        secondList.add(secondListItem2);//对应明天
        LinkagePicker picker = new LinkagePicker(this, firstList, secondList);
        picker.setSelectedItem("12小时制", "9点");
        picker.setLineConfig(new WheelView.LineConfig(0));//使用最长的线
        picker.setOnLinkageListener(new LinkagePicker.OnLinkageListener() {

            @Override
            public void onPicked(String first, String second, String third) {
                showToast(first + "-" + second + "-" + third);
            }
        });
        picker.show();
    }

    public void onConstellationPicker(View view) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "水瓶", "双鱼", "白羊", "金牛", "双子", "巨蟹",
                        "狮子", "处女", "天秤", "天蝎", "射手", "摩羯"
                } : new String[]{
                        "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer",
                        "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn"
                });
        picker.setLabel(isChinese ? "座" : "");
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(24);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(22);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(22);
        picker.setTextColor(0xFFEE0000, 0xFF999999);
        WheelView.LineConfig config = new WheelView.LineConfig();
        config.setColor(0xFFEE0000);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                showToast("index=" + index + ", item=" + item);
            }
        });
        picker.show();
    }

    public void onNumberPicker(View view) {
        NumberPicker picker = new NumberPicker(this);
        picker.setWidth(picker.getScreenWidthPixels() / 2);
        picker.setLineVisible(false);
        picker.setOffset(2);//偏移量
        picker.setRange(145, 200, 1);//数字范围
        picker.setSelectedItem(172);
        picker.setLabel("厘米");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                showToast("index=" + index + ", item=" + item.intValue());
            }
        });
        picker.show();
    }

    public void onAddressPicker(View view) {
        new AddressInitTask(this).execute("贵州", "毕节", "纳雍");
    }

    public void onAddress2Picker(View view) {
        try {
            ArrayList<Province> data = new ArrayList<Province>();
            String json = ConvertUtils.toString(getAssets().open("city2.json"));
            data.addAll(JSON.parseArray(json, Province.class));
            AddressPicker picker = new AddressPicker(this, data);
            picker.setCycleDisable(true);
            picker.setHideProvince(true);
            picker.setSelectedItem("贵州", "贵阳", "花溪");
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(Province province, City city, County county) {
                    if (county == null) {
                        showToast("province : " + province + ", city: " + city);
                    } else {
                        showToast("province : " + province + ", city: " + city + ", county: " + county);
                    }
                }
            });
            picker.show();
        } catch (Exception e) {
            showToast(e.toString());
        }
    }


    public void onAddress3Picker(View view) {
        new AddressInitTask(this, true).execute("四川", "阿坝");
    }

    public void onColorPicker(View view) {
        ColorPicker picker = new ColorPicker(this);
        picker.setInitColor(0xDD00DD);
        picker.setOnColorPickListener(new ColorPicker.OnColorPickListener() {
            @Override
            public void onColorPicked(int pickedColor) {
                showToast(ConvertUtils.toColorString(pickedColor));
            }
        });
        picker.show();
    }

    public void onFilePicker(View view) {
        //noinspection MissingPermission
        FilePicker picker = new FilePicker(this, FilePicker.FILE);
        picker.setShowHideDir(false);
        //picker.setAllowExtensions(new String[]{".apk"});
        picker.setOnFilePickListener(new FilePicker.OnFilePickListener() {
            @Override
            public void onFilePicked(String currentPath) {
                showToast(currentPath);
            }
        });
        picker.show();
    }

    public void onDirPicker(View view) {
        //noinspection MissingPermission
        FilePicker picker = new FilePicker(this, FilePicker.DIRECTORY);
        picker.setRootPath(StorageUtils.getExternalRootPath() + "Download/");
        picker.setOnFilePickListener(new FilePicker.OnFilePickListener() {
            @Override
            public void onFilePicked(String currentPath) {
                showToast(currentPath);
            }
        });
        picker.show();
    }

    public void onContact(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:liyujiang_tk@yeah.net"));
        intent.putExtra(Intent.EXTRA_CC, new String[]
                {"1032694760@qq.com"});
        intent.putExtra(Intent.EXTRA_EMAIL, "");
        intent.putExtra(Intent.EXTRA_TEXT, "欢迎提供意您的见或建议");
        startActivity(Intent.createChooser(intent, "选择邮件客户端"));
    }

}
