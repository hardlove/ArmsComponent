package me.jessyan.armscomponent.commonsdk.utils;


import me.jessyan.armscomponent.commonsdk.BuildConfig;
import timber.log.Timber;


public class MLogger {

    /**
     * 使用前需要在项目中初始化
     */
    public static void init() {
        if (BuildConfig.LOG_DEBUG) {//Timber初始化
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new FileLoggingTree());
        }
    }

    private String tag;

    private MLogger(String tag) {
        this.tag = tag;
    }

    public static MLogger tag(String tag) {
        return new MLogger(tag);
    }
    //=================================================================================
    public void d(String message, Object... args) {
        Timber.tag(tag).d(message, args);
    }

    public void d(Throwable t) {
        Timber.tag(tag).d(t);
    }

    public void d(Throwable t, String message, Object... args) {
        Timber.tag(tag).d(t, message, args);
    }

    //=================================================================================
    public void i(String message, Object... args) {
        Timber.tag(tag).i(message, args);
    }

    public void i(Throwable t) {
        Timber.tag(tag).i(t);
    }

    public void i(Throwable t, String message, Object... args) {
        Timber.tag(tag).i(t, message, args);
    }

    //=================================================================================
    public void w(String message, Object... args) {
        Timber.tag(tag).w(message, args);
    }

    public void w(Throwable t) {
        Timber.tag(tag).w(t);
    }

    public void w(Throwable t, String message, Object... args) {
        Timber.tag(tag).w(t, message, args);
    }

    //=================================================================================
    public void e(String message, Object... args) {
        Timber.tag(tag).e(message, args);
    }

    public void e(Throwable t) {
        Timber.tag(tag).e(t);
    }

    public void e(Throwable t, String message, Object... args) {
        Timber.tag(tag).e(t, message, args);
    }


    private static final class FileLoggingTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            // TODO: 2018/8/23 release版本将日志写入文件中
        }
    }

}
