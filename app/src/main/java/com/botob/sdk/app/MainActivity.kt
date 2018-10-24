package com.botob.sdk.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.botob.samplesdk.equation.reader.EquationSolver
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files


class MainActivity : AppCompatActivity() {
    companion object {
        /**
         * The list of permissions to request at runtime.
         */
        val PERMISSIONS = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()
    }

    /**
     * Checks for runtime permissions.
     *
     * @return true if all good; otherwise, false.
     */
    private fun checkPermissions(): Boolean {
        var result: Int
        val listPermissionsNeeded = ArrayList<String>()
        for (permission in PERMISSIONS) {
            result = ContextCompat.checkSelfPermission(this, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 100)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // All good.
            } else {
                handleMissingPermissions()
            }
        } else {
            handleMissingPermissions()
        }
    }

    /**
     * Shows a Toast message and disables solve button if permissions are missing.
     */
    private fun handleMissingPermissions() {
        val button = findViewById<Button>(R.id.button_solve)
        button.isEnabled = false
        Toast.makeText(applicationContext, getString(R.string.main_activity_message_storage_must_enabled_via_app_info), Toast.LENGTH_LONG)
                .show()
    }

    /**
     * Executes actions upon solve button click.
     */
    fun onSolveButtonClick(view: View) {
        solve()
    }

    /**
     * Solves the equations from the input file provided by the user.
     */
    private fun solve() {
        val path = findViewById<EditText>(R.id.text_filepath).text.toString()
        if (File(path).exists()) {
            try {
                findViewById<TextView>(R.id.text_result).text = EquationSolver(FileInputStream(path)).formatSolution()
            } catch (exception: Exception) {
                Toast.makeText(applicationContext, getString(R.string.main_activity_message_resolution_failed, exception.toString()), Toast.LENGTH_LONG)
                        .show()
            }
        } else {
            Toast.makeText(applicationContext, getString(R.string.main_activity_message_filepath_does_not_exist), Toast.LENGTH_LONG)
                    .show()
        }
    }
}
