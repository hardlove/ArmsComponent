package com.hardlove.cl.im.mvp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardlove.cl.im.R;
import com.hardlove.cl.im.R2;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.Iterator;

import butterknife.BindView;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.TypingMessage.TypingStatus;
import io.rong.imlib.model.Conversation;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * Created by Chenlu on 2018/11/20
 * Email:chenlu@globalroam.com
 */
public class ConversationActivity extends BaseActivity {
    @BindView(R2.id.tv_title_left)
    TextView mTitle;
    @BindView(R2.id.iv_back)
    ImageView mBack;
    ConversationFragment mConversationFragment;

    private Conversation.ConversationType mConversationType;
    private String mTargetId;
    private String mTitle1;

    private Handler mHandler;
    private final String TextTypingTitle = "对方正在输入...";
    private final String VoiceTypingTitle = "对方正在讲话...";

    private static final int SET_TEXT_TYPING_TITLE = 0x01;//对方正在输入
    private static final int SET_VOICE_TYPING_TITLE = 0x02;//对方正在讲话
    private static final int SET_STOP_TYPING_TITLE = 0x03;//对方停止输入或讲话


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.im_conversion_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mConversationFragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

        mTitle1 = getIntent().getData().getQueryParameter("title");
        mTargetId = getIntent().getData().getQueryParameter("targetId");
        mTitle.setText(mTitle1);
        setTypingStatusListener();
    }

    /**
     * 监听对方输入状态改变
     */
    private void setTypingStatusListener() {
        mHandler = new Handler(msg -> {
            switch (msg.what) {
                case SET_TEXT_TYPING_TITLE:
                    ArmsUtils.makeText(this,TextTypingTitle);
                    break;
                case SET_VOICE_TYPING_TITLE:
                    ArmsUtils.makeText(this,VoiceTypingTitle);
                    break;
                case SET_STOP_TYPING_TITLE:
                    ArmsUtils.makeText(this,"对方停止输入");
                    break;
                default:
                    break;
            }
            return true;
        });

        RongIMClient.setTypingStatusListener((type, targetId, typingStatusSet) -> {
            //当输入状态的会话类型和targetID与当前会话一致时，才需要显示
            if (type.equals(mConversationType) && targetId.equals(mTargetId)) {
                int count = typingStatusSet.size();
                //count表示当前会话中正在输入的用户数量，目前只支持单聊，所以判断大于0就可以给予显示了
                if (count > 0) {
                    Iterator iterator = typingStatusSet.iterator();
                    TypingStatus status = (TypingStatus) iterator.next();
                    String objectName = status.getTypingContentType();

                    MessageTag textTag = TextMessage.class.getAnnotation(MessageTag.class);
                    MessageTag voiceTag = VoiceMessage.class.getAnnotation(MessageTag.class);
                    //匹配对方正在输入的是文本消息还是语音消息
                    if (objectName.equals(textTag.value())) {
                        mHandler.sendEmptyMessage(SET_TEXT_TYPING_TITLE);
                    } else if (objectName.equals(voiceTag.value())) {
                        mHandler.sendEmptyMessage(SET_VOICE_TYPING_TITLE);
                    }
                } else {//当前会话没有用户正在输入，标题栏仍显示原来标题
                    mHandler.sendEmptyMessage(SET_STOP_TYPING_TITLE);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        RongIMClient.setTypingStatusListener(null);

    }
}
