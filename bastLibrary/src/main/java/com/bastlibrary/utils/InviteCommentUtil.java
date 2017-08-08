package com.bastlibrary.utils;

import android.app.Activity;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class InviteCommentUtil {
    private String mDateFormat = "yyyy-MM-dd";
    private String mInviteCommentTime;
//    /**
//     * 选择哪天弹邀请评论框
//     */
//    public void startComment(final Activity activity) {
//        mInviteCommentTime = PreferenceUtils.getPreferenceString(activity, "inviteCommentTime", TimeUtil.getCurrentTime(mDateFormat));
//        String saveCommentTime = PreferenceUtils.getPreferenceString(activity, "saveCommentTime", TimeUtil.getCurrentTime(mDateFormat));
//        int compareDateValue = TimeUtil.compareDate(mInviteCommentTime, saveCommentTime, mDateFormat);
//        if (compareDateValue == 1) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(
//                    activity);
//            int nowReadingTotal = ReadUtil.getReadedTotal();
//            builder.setMessage(Html.fromHtml("您已经累计阅读<font color=#FF0000>" + nowReadingTotal + "</font>字，再接再厉哦！如果喜欢我，希望您能在应用市场给予<font color=#FF0000>五星</font>好评！"));
//            builder.setTitle("求好评");
//            builder.setPositiveButton("好评鼓励",
//                    new android.content.DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            setComment(activity);
//                            try {
//                                Intent intent = new Intent(
//                                        "android.intent.action.VIEW");
//                                intent.setData(Uri
//                                        .parse("market://details?id=com.android.xiaomolongstudio.weiyan"));
//                                activity.startActivity(intent);
//                                activity.overridePendingTransition(
//                                        R.anim.enter_right_to_left, R.anim.exit);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            dialog.dismiss();
//                        }
//                    });
//            builder.setNegativeButton("下次再说",
//                    new android.content.DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            setComment(activity);
//                            dialog.dismiss();
//                        }
//                    });
//            builder.create().show();
//        }
//    }
//    /**
//     * 保存，直到下周再提示
//     */
//    private void setComment(Activity activity) {
//        PreferenceUtils.setPreferenceString(activity, "saveCommentTime", mInviteCommentTime);
//    }
}