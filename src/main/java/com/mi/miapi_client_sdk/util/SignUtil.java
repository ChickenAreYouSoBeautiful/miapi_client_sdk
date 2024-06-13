package com.mi.miapi_client_sdk.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author mi11
 * @version 1.0
 * @project miApi_interface
 * @description
 * @ClassName SignUtil
 */
public class SignUtil {
    public static String genSign(String signString){
        Digester sign = new Digester(DigestAlgorithm.SHA256);
        return sign.digestHex(signString);
    }
}
