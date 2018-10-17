package com.example.administrator.xposeapk;

import android.content.ContentResolver;
import android.content.ContentUris;import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;import android.widget.Toast;
public class Util {
    public static void addContact(Context context, String name, String phoneNumber) {
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();
        // 向RawContacts.CONTENT_URI空值插入，
        // 先获取Android系统返回的rawContactId
        // 后面要基于此id插入值
        Uri rawContactUri = context.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        // 内容类型
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // 联系人的电话号码
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
        // 电话类型
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        // 向联系人电话号码URI添加电话号码
        context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();
        //以下为插入e-mail信息，不需要可以注释掉//
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        // 联系人的Email地址//
        values.put(ContactsContract.CommonDataKinds.Email.DATA, "zhangphil@xxx.com");
        // 电子邮件的类型//
        values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);//
        // 向联系人Email URI添加Email数据
        context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        Toast.makeText(context, "联系人数据添加成功", Toast.LENGTH_SHORT).show();
    }

    public static void clearContact(Context context) {
        ContentResolver cr = context.getContentResolver();
        // 查询contacts表的所有记录
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        // 如果记录不为空
        if (cursor.getCount() > 0) {
            // 游标初始指向查询结果的第一条记录的上方，执行moveToNext函数会判断
            // 下一条记录是否存在，如果存在，指向下一条记录。否则，返回false。
            while (cursor.moveToNext()) {
//                String rawContactId = "";
//                // 从Contacts表当中取得ContactId//
//                String id = cursor.getString(cursor
//                        .getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //根据姓名求id
                Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
                Cursor cursor1 = cr.query(uri, new String[]{ContactsContract.Data._ID}, "display_name=?", new String[]{name}, null);
                if (cursor1.moveToFirst()) {
                    int id = cursor1.getInt(0);
                    //根据id删除data中的相应数据
                    cr.delete(uri, "display_name=?", new String[]{name});
                    uri = Uri.parse("content://com.android.contacts/data");
                    cr.delete(uri, "raw_contact_id=?", new String[]{id + ""});
                }
            }
        }
    }








}