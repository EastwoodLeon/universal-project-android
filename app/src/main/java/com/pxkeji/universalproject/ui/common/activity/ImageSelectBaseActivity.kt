package com.pxkeji.universalproject.ui.common.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager

import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import com.pxkeji.eentertainment.ui.common.dialog.ImageSelectDialog

import com.pxkeji.universalproject.util.ImageUtil
import com.pxkeji.util.LogUtil


import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File


abstract class ImageSelectBaseActivity : SimpleBaseActivity() {

    companion object {
        private const val TAG = "ImageSelect"
        private const val IMAGE_SELECT_DIALOG = "ImageSelectDialog"
        private const val REQUEST_TAKE_PHOTO = 101
        private const val REQUEST_PICK_PHOTO = 102
    }

    protected var mImageSelectDialog: ImageSelectDialog? = null

    protected var mImageUri: Uri? = null

    abstract fun handleCompressedImageFile(file: File)

    protected fun createImageSelectDialog(): ImageSelectDialog {
        val dialog = ImageSelectDialog.newInstance()
        dialog.mOnClickListener = object : ImageSelectDialog.Companion.OnClickListener {
            override fun onTakePhotoClick() {
                mImageSelectDialog?.dismiss()
                if (ContextCompat.checkSelfPermission(this@ImageSelectBaseActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this@ImageSelectBaseActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_TAKE_PHOTO)
                } else {
                    openCamera()
                }
            }

            override fun onChooseFromAlbumClick() {
                mImageSelectDialog?.dismiss()
                if (ContextCompat.checkSelfPermission(this@ImageSelectBaseActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this@ImageSelectBaseActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PICK_PHOTO)
                } else {
                    openAlbum()
                }
            }

            override fun onCancelClick() {
                mImageSelectDialog?.dismiss()
            }

        }
        return dialog
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {



        when(requestCode) {
            REQUEST_TAKE_PHOTO -> {
                if (resultCode == Activity.RESULT_OK) {
                    mImageUri?.let { imageUri ->

                        object : AsyncTask<Unit, Unit, String>() {
                            override fun doInBackground(vararg p0: Unit?): String = ImageUtil.writeFileWithStream(contentResolver.openInputStream(imageUri), File(externalCacheDir, "stream_image.jpg"))

                            override fun onPostExecute(result: String?) {
                                if (result == null)
                                    return

                                val file = File(result)
                                if (file.exists()) {
                                    compressImage(result)
                                }
                            }
                        }.execute()

                    }
                }
            }
            REQUEST_PICK_PHOTO -> {

                if (resultCode == Activity.RESULT_OK) {


                    if (data == null)
                        return


                    object : AsyncTask<Unit, Unit, String>() {
                        override fun doInBackground(vararg p0: Unit?): String = ImageUtil.getImagePathFromUri(this@ImageSelectBaseActivity, data.data)

                        override fun onPostExecute(result: String?) {

                            if (result == null)
                                return

                            val file = File(result)
                            if (file.exists()) {

                                compressImage(result)
                            } else {

                            }
                        }
                    }.execute()



                }
            }
        }
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            REQUEST_TAKE_PHOTO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                }
            }
            REQUEST_PICK_PHOTO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum()
                }
            }
        }
    }

    private fun openCamera() {
        val outputImage = File(externalCacheDir, "output_image.jpg")
        if (outputImage.exists()) {
            outputImage.delete()
        }

        outputImage.createNewFile()

        mImageUri = if (Build.VERSION.SDK_INT >= 24) {
            FileProvider.getUriForFile(this@ImageSelectBaseActivity, "com.pxkeji.carmanager.ucrop.provider", outputImage)
        } else {
            Uri.fromFile(outputImage)
        }

        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri)
        startActivityForResult(intent, REQUEST_TAKE_PHOTO)
    }

    private fun openAlbum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.setType("image/*")
        startActivityForResult(intent, REQUEST_PICK_PHOTO)

    }

    protected fun showImageSelectDialog() {
        if (mImageSelectDialog == null) {
            mImageSelectDialog = createImageSelectDialog()
        }
        mImageSelectDialog!!.show(supportFragmentManager, IMAGE_SELECT_DIALOG)
    }

    private fun compressImage(path: String) {

        Luban.with(this@ImageSelectBaseActivity)
                .load(path)
                .ignoreBy(100)
                .setTargetDir(ImageUtil.getCompressedDir(this@ImageSelectBaseActivity))
                .setCompressListener(object : OnCompressListener {
                    override fun onStart() {
                        LogUtil.w("onStart", "onStart")
                    }

                    override fun onSuccess(file: File) {
                        LogUtil.w("onSuccess", file.path)
                        handleCompressedImageFile(file)
                    }

                    override fun onError(e: Throwable) {
                        LogUtil.w("onError", e.message)
                    }
                })
                .launch()


    }


}