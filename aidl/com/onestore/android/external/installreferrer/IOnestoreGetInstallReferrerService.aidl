package com.onestore.android.external.installreferrer;

/**
 * An API to onestore install referrer.
 */
interface IOnestoreGetInstallReferrerService {
     /**
      * onestore install referrer.
      *
      * @param arguments Bundle contains Caller’s package name.
      *     key: "package_name" value: string
      *         The package name of the caller, used for disambiguation.
      * @return The returned Bundle contains the following:
      * <pre>
      *     key: "onestore_pid" value: string
      *     key: "install_referrer" value: string
      *         The referrer url of the installed package
      *     key: "referrer_click_timestamp_seconds" value: long
      *         The timestamp in milliseconds when referrer click happens
      *     key: "install_begin_timestamp_seconds" value: long
      *         The timestamp in milliseconds when installation begins
      * </pre>
      */
      Bundle getInstallReferrer(in Bundle paramaters);
}

