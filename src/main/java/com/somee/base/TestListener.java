package com.somee.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.somee.utils.Log;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        Log.info("===================================================================");
        Log.info("BẮT ĐẦU TEST CONTEXT: " + context.getName());
        Log.info("Số lượng test methods trong context: " + context.getAllTestMethods().length);
        Log.info("===================================================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("-------------------------------------------------------------------");
        Log.info("BẮT ĐẦU TEST CASE: " + result.getName());
        Log.info("Mô tả: " + result.getMethod().getDescription());
        Log.info("-------------------------------------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("-------------------------------------------------------------------");
        Log.info("TEST CASE THÀNH CÔNG: " + result.getName());
        Log.info("Thời gian chạy: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
        Log.info("-------------------------------------------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("-------------------------------------------------------------------");
        Log.error("TEST CASE THẤT BẠI: " + result.getName());
        Log.error("Thời gian chạy: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
        if (result.getThrowable() != null) {
            Log.error("Lỗi: " + result.getThrowable().getMessage());
            result.getThrowable().printStackTrace();
        }
        Log.error("-------------------------------------------------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn("-------------------------------------------------------------------");
        Log.warn("TEST CASE BỊ BỎ QUA: " + result.getName());
        Log.warn("Lý do bỏ qua: " + (result.getThrowable() != null ? result.getThrowable().getMessage() : "Không có lý do cụ thể"));
        Log.warn("-------------------------------------------------------------------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("TEST CASE THẤT BẠI NHƯNG TRONG NGƯỠNG THÀNH CÔNG CHO PHÉP: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("===================================================================");
        Log.info("KẾT THÚC TEST CONTEXT: " + context.getName());
        Log.info("Tổng số test methods: " + context.getAllTestMethods().length);
        Log.info("Số test thành công: " + context.getPassedTests().size());
        Log.info("Số test thất bại: " + context.getFailedTests().size());
        Log.info("Số test bị bỏ qua: " + context.getSkippedTests().size());
        Log.info("===================================================================");
    }
}