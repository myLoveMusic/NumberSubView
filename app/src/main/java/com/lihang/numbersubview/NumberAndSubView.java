package com.lihang.numbersubview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 一指流沙 on 2016/9/26.
 */
public class NumberAndSubView extends LinearLayout implements View.OnClickListener {

    private LayoutInflater mInflater;
    private Button mAddBtn;
    private Button mSubBtn;
    private TextView mNumTv;

    private int value;
    private int minValue;
    private int maxValue;

    private OnButtonClickListener mOnButtonClickListener;


    public interface OnButtonClickListener {
        void onButtonAddClick(View view, int value);

        void onButtonSubClick(View view, int value);

    }

    public NumberAndSubView(Context context) {
        this(context, null);
    }

    public NumberAndSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAndSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        initView();
        if (attrs != null) {
            TintTypedArray tta = TintTypedArray.obtainStyledAttributes(
                    context, attrs, R.styleable.NumberAndSubView, defStyleAttr, 0
            );
            int val = tta.getInt(R.styleable.NumberAndSubView_value, 0);
            setValue(val);

            int minValue = tta.getInt(R.styleable.NumberAndSubView_minValue, 0);
            setMinValue(minValue);

            int maxValue = tta.getInt(R.styleable.NumberAndSubView_maxValue, 0);
            setMaxValue(maxValue);

            Drawable addBtnDrawable = tta.getDrawable(R.styleable.NumberAndSubView_addBtnBg);
            setAddBtnBackgroud(addBtnDrawable);

            Drawable subBtnDrawable = tta.getDrawable(R.styleable.NumberAndSubView_subBtnBg);
            setSubBtnBackgroud(subBtnDrawable);

            int numTvDrawable = tta.getResourceId(R.styleable.NumberAndSubView_numTvBg, android.R.color.transparent);
            setNumTvBackgroud(numTvDrawable);

            tta.recycle();
        }
    }

    private void setNumTvBackgroud(int numTvDrawable) {
        mNumTv.setBackgroundResource(numTvDrawable);
    }

    private void setAddBtnBackgroud(Drawable addBtnDrawable) {
        this.mAddBtn.setBackground(addBtnDrawable);
    }

    private void setSubBtnBackgroud(Drawable subBtnDrawable) {
        this.mSubBtn.setBackground(subBtnDrawable);
    }

    private void initView() {                                                //是否放在根目录下
        View view = mInflater.inflate(R.layout.wieght_number_add_sub, this, true);
        mAddBtn = (Button) view.findViewById(R.id.btn_add);
        mSubBtn = (Button) view.findViewById(R.id.btn_sub);
        mNumTv = (TextView) view.findViewById(R.id.tv_num);

        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                numAdd();
                if (mOnButtonClickListener != null) {
                    mOnButtonClickListener.onButtonAddClick(v, value);
                }
                break;
            case R.id.btn_sub:
                numSub();
                if (mOnButtonClickListener != null) {
                    mOnButtonClickListener.onButtonSubClick(v, value);
                }
                break;
        }
    }

    //加方法
    private void numAdd() {
        if (value < maxValue) {
            value++;
        }
        mNumTv.setText(String.valueOf(value));
    }

    //减方法
    private void numSub() {
        if (value > minValue) {
            value--;
        }
        mNumTv.setText(String.valueOf(value));
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mOnButtonClickListener = onButtonClickListener;
    }

    public int getValue() {
        String val = mNumTv.getText().toString();
        if (val != null && !"".equals(val)) {
            this.value = Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        mNumTv.setText(String.valueOf(value));
        this.value = value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

}
