package com.google.android.apps.nexuslauncher.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;
import fr.letmethink.lawnchair.colors.ColorEngine;
import fr.letmethink.lawnchair.colors.ColorEngine.OnColorChangeListener;
import fr.letmethink.lawnchair.colors.ColorEngine.ResolveInfo;
import fr.letmethink.lawnchair.colors.ColorEngine.Resolvers;
import fr.letmethink.lawnchair.font.CustomFontManager;
import com.android.launcher3.views.DoubleShadowBubbleTextView;
import org.jetbrains.annotations.NotNull;

public class DoubleShadowTextView extends TextView implements OnColorChangeListener {
    private final DoubleShadowBubbleTextView.ShadowInfo mShadowInfo;

    public DoubleShadowTextView(Context context) {
        this(context, null);
    }

    public DoubleShadowTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleShadowTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mShadowInfo = new DoubleShadowBubbleTextView.ShadowInfo(context, attrs, defStyleAttr);
        setShadowLayer(Math.max(mShadowInfo.keyShadowBlur + mShadowInfo.keyShadowOffset, mShadowInfo.ambientShadowBlur), 0f, 0f, mShadowInfo.keyShadowColor);
        CustomFontManager.Companion.getInstance(context).loadCustomFont(this, attrs);
        ColorEngine.getInstance(context).addColorChangeListeners(this, Resolvers.WORKSPACE_ICON_LABEL);
    }

    protected void onDraw(Canvas canvas) {
        if (mShadowInfo.skipDoubleShadow(this)) {
            super.onDraw(canvas);
            return;
        }
        getPaint().setShadowLayer(mShadowInfo.ambientShadowBlur, 0.0f, 0.0f, mShadowInfo.ambientShadowColor);
        super.onDraw(canvas);
        getPaint().setShadowLayer(mShadowInfo.keyShadowBlur, 0.0f, mShadowInfo.keyShadowOffset, mShadowInfo.keyShadowColor);
        super.onDraw(canvas);
    }

    @Override
    public void onColorChange(@NotNull ResolveInfo resolveInfo) {
        setTextColor(resolveInfo.getColor());
    }
}
