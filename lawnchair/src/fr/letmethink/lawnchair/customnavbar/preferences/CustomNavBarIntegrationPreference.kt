/*
 *     Copyright (C) 2019 Lawnchair Team.
 *
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

package fr.letmethink.lawnchair.customnavbar.preferences

import android.content.Context
import android.util.AttributeSet
import fr.letmethink.lawnchair.BlankActivity
import fr.letmethink.lawnchair.LawnchairLauncher
import fr.letmethink.lawnchair.customnavbar.CustomNavBar
import fr.letmethink.lawnchair.preferences.ResumablePreference
import fr.letmethink.lawnchair.preferences.StyledSwitchPreferenceCompat
import com.android.launcher3.R
import com.android.launcher3.util.PackageManagerHelper

class CustomNavBarIntegrationPreference(context: Context, attrs: AttributeSet?) :
        StyledSwitchPreferenceCompat(context, attrs), ResumablePreference {

    private val manager = CustomNavBar.getInstance(context)
    private val isInstalled = manager.isInstalled

    init {
        isPersistent = false
        updateSummary()
        super.setChecked(manager.enableIntegration)
    }

    private fun updateSummary() {
        summary = context.getString(when {
            isInstalled -> R.string.customnavbar_back_hiding_desc
            else -> R.string.customnavbar_install
        })
    }

    override fun onResume() {
        updateSummary()
    }

    override fun onClick() {
        if (isInstalled) {
            if (manager.isPermissionGranted) {
                super.onClick()
            } else {
                BlankActivity.requestPermission(
                        context, CustomNavBar.MODIFY_NAVBAR_PERMISSION,
                        LawnchairLauncher.REQUEST_PERMISSION_MODIFY_NAVBAR) {
                    if (it) {
                        super.onClick()
                    }
                }
            }
        } else {
            context.startActivity(PackageManagerHelper(context).getMarketIntent(CustomNavBar.PACKAGE))
        }
    }

    override fun setChecked(checked: Boolean) {
        super.setChecked(checked)
        manager.enableIntegration = checked
    }
}
