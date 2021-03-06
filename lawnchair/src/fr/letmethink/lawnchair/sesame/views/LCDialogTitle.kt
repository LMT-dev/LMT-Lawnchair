/*
 *     This file is part of Lawnchair Launcher.
 *
 *     Lawnchair Launcher is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Lawnchair Launcher is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Lawnchair Launcher.  If not, see <https://www.gnu.org/licenses/>.
 */

package fr.letmethink.lawnchair.sesame.views

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.DialogTitle
import android.util.AttributeSet
import fr.letmethink.lawnchair.font.CustomFontManager
import fr.letmethink.lawnchair.setCustomFont

/**
 * Simple dialog title class to use in externally displayed layouts where we can't programmatically change the design otherwise
 */
@SuppressLint("RestrictedApi")
class LCDialogTitle(context: Context?, attrs: AttributeSet?) : DialogTitle(context, attrs) {
    init {
        setCustomFont(CustomFontManager.FONT_DIALOG_TITLE)
    }
}