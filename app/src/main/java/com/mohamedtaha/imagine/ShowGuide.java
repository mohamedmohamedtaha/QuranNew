package com.mohamedtaha.imagine;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mohamedtaha.imagine.helper.SharedPerefrenceHelper;
import com.mohamedtaha.imagine.informationInrto.TapTarget;
import com.mohamedtaha.imagine.informationInrto.TapTargetSequence;
import com.mohamedtaha.imagine.informationInrto.TapTargetView;
import com.mohamedtaha.imagine.ui.activities.NavigationDrawaberActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//@ScopeActivity
public class ShowGuide extends AppCompatActivity {
    private Activity context;
    private TapTargetSequence sequence;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    public ShowGuide() {
    }

    public void getGuide(Activity context, Toolbar toolbar, BottomNavigationView bottomNavigationView) {
        this.context = context;
        this.toolbar = toolbar;
        this.bottomNavigationView = bottomNavigationView;
        showInformation();
    }

    TapTargetSequence.Listener listenerForEndInformation = new TapTargetSequence.Listener() {
        @Override
        public void onSequenceFinish() {
        }

        @Override
        public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
            Log.d("TapTargetView", "Clicked on " + lastTarget.id());
        }

        @Override
        public void onSequenceCanceled(TapTarget lastTarget) {
            final AlertDialog dialog = new AlertDialog.Builder(context)
                    .setPositiveButton(context.getString(R.string.sorry), null).show();
            TapTargetView.showFor(dialog,
                    TapTarget.forView(dialog.getButton(DialogInterface.BUTTON_POSITIVE), context.getString(R.string.end), context.getString(R.string.description_string))
                            .cancelable(true)
                            .transparentTarget(true)
                            .titleTextColor(android.R.color.white)
                            .outerCircleColor(R.color.color_background_drawable)
                            .outerCircleAlpha(0.9f)
                            //      .targetCircleColor(R.color.colorAccent)
                            .tintTarget(false), new TapTargetView.Listener() {
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);
                            dialog.dismiss();
                        }

                        @Override
                        public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                            dialog.dismiss();

                        }
                    });
        }
    };

    private TapTarget showInformationInToolbar(int id_menu, String title, String description) {
        // This tap target will target the tool bar
        TapTarget tapTarget = TapTarget.forToolbarMenuItem(toolbar, id_menu, title, description)
                .transparentTarget(true)
                .outerCircleColor(R.color.color_background_drawable)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                .targetCircleColor(R.color.colorAccent)
                .titleTextSize(18)
                .tintTarget(false);
        return tapTarget;
    }

    private void customInfo(int id_menu, int title, int description, TapTargetView.Listener listener) {
        TapTargetView.showFor(context, TapTarget.forView(context.findViewById(id_menu), context.getString(title)
                , context.getString(description))
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.color_background_drawable)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                // .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), listener);
    }

    public void showInformation() {
        // You don't always need a sequence, and for that there's a single time tap target
        TapTargetView.Listener listener = new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                bottomNavigationView.setSelectedItemId(R.id.read_parts);
                setTwoShow();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");
            }
        };
        customInfo(R.id.read_quran, R.string.spectial_button, R.string.read_string, listener);
        // setInformation();

//        toolbar.inflateMenu(R.menu.menu);
//        sequence = new TapTargetSequence(context)
//                .targets(
//                        showInformationInToolbar(R.id.action_search, context.getString(R.string.spectial_button), context.getString(R.string.search_string)).id(1),
//                        showInformationInToolbar(R.id.action_share, context.getString(R.string.spectial_button), context.getString(R.string.share_string)).id(2),
//                        //This for R.id.spectial_button
//                        TapTarget.forToolbarOverflow(toolbar, "   هذا الزر خاص",
//                                "      بالأربعون النووية  " +
//                                        "\n" +
//                                        "      ضبط زمن الأشعارات  " +
//                                        "\n" +
//                                        "      عرض طريقة الاستخدام مرة أخرى    "
//                                        + "\n" +
//                                        "      وتقييم التطبيق     " +
//                                        "\n" +
//                                        "      ومراسلتنا    ")
//                                .outerCircleColor(R.color.color_background_drawable)
//                                .outerCircleAlpha(0.9f)
//                                .textColor(android.R.color.white)
//                                //  .targetCircleColor(R.color.colorAccent)
//                                .transparentTarget(true)
//                                .tintTarget(false)
//                ).listener(listenerForEndInformation);


    }

    private void setInformation() {
        toolbar.inflateMenu(R.menu.menu);
        sequence = new TapTargetSequence(context);
        sequence.targets(
                //  showInformationInToolbar(R.id.action_search, context.getString(R.string.spectial_button), context.getString(R.string.search_string)).id(0),
                showInformationInToolbar(R.id.action_share, context.getString(R.string.spectial_button), context.getString(R.string.share_string)).id(1),
                //This for R.id.spectial_button
                TapTarget.forToolbarOverflow(toolbar, "   هذا الزر خاص",
                        "      بالأربعون النووية  " +
                                "\n" +
                                "      ضبط زمن الأشعارات  " +
                                "\n" +
                                "      عرض طريقة الاستخدام مرة أخرى    "
                                + "\n" +
                                "      وتقييم التطبيق     " +
                                "\n" +
                                "      ومراسلتنا    ")
                        .outerCircleColor(R.color.color_background_drawable)
                        .outerCircleAlpha(0.9f)
                        .textColor(android.R.color.white)
                        //  .targetCircleColor(R.color.colorAccent)
                        .transparentTarget(true)
                        .tintTarget(false)
        ).listener(listenerForEndInformation);
    }

    private void setTwoShow() {
        TapTargetView.Listener listener = new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                bottomNavigationView.setSelectedItemId(R.id.sound_quran);
                setShowThreeItem();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        };
        customInfo(R.id.read_parts, R.string.spectial_button, R.string.read_parts_string, listener);

    }

    private void setShowThreeItem() {
        TapTargetView.Listener listener = new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                bottomNavigationView.setSelectedItemId(R.id.prayer_times);
                setShowFourItem();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        };
        customInfo(R.id.sound_quran, R.string.spectial_button, R.string.sound_string, listener);
    }

    private void setShowFourItem() {
        TapTargetView.Listener listener = new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                bottomNavigationView.setSelectedItemId(R.id.azkar);
                setShowFiveItem();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        };
        customInfo(R.id.prayer_times, R.string.spectial_button, R.string.set_prayer_times, listener);

    }

    private void setShowFiveItem() {
        TapTargetView.Listener listener = new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
//                sequence.start();
                //   setInformation();
                getshowGuideTrue();

            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");
            }
        };
        customInfo(R.id.azkar, R.string.spectial_button, R.string.read_azkar, listener);

    }

    private void getshowGuideTrue() {
        SharedPerefrenceHelper.putBooleanForWayUsing(context, NavigationDrawaberActivity.IS_FIRST_TIME_WAY_USING, true);
    }
}
