package org.asamk.signal.manager.config;

import org.bouncycastle.util.encoders.Hex;
import org.signal.libsignal.protocol.InvalidKeyException;
import org.signal.libsignal.protocol.ecc.Curve;
import org.signal.libsignal.protocol.ecc.ECPublicKey;
import org.whispersystems.signalservice.api.push.TrustStore;
import org.whispersystems.signalservice.internal.configuration.SignalCdnUrl;
import org.whispersystems.signalservice.internal.configuration.SignalCdsiUrl;
import org.whispersystems.signalservice.internal.configuration.SignalKeyBackupServiceUrl;
import org.whispersystems.signalservice.internal.configuration.SignalProxy;
import org.whispersystems.signalservice.internal.configuration.SignalServiceConfiguration;
import org.whispersystems.signalservice.internal.configuration.SignalServiceUrl;
import org.whispersystems.signalservice.internal.configuration.SignalStorageUrl;
import org.whispersystems.signalservice.internal.configuration.SignalSvr2Url;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import okhttp3.Dns;
import okhttp3.Interceptor;

class LiveConfig {

    private final static byte[] UNIDENTIFIED_SENDER_TRUST_ROOT = Base64.getDecoder()
            .decode("BXu6QIKVz5MA8gstzfOgRQGqyLqOwNKHL6INkv3IHWMF");
    private final static String CDSI_MRENCLAVE = "0f6fd79cdfdaa5b2e6337f534d3baf999318b0c462a7ac1f41297a3e4b424a57";
    private final static String SVR2_MRENCLAVE = "6ee1042f9e20f880326686dd4ba50c25359f01e9f733eeba4382bca001d45094";

    private final static String KEY_BACKUP_ENCLAVE_NAME = "e18376436159cda3ad7a45d9320e382e4a497f26b0dca34d8eab0bd0139483b5";
    private final static byte[] KEY_BACKUP_SERVICE_ID = Hex.decode(
            "3a485adb56e2058ef7737764c738c4069dd62bc457637eafb6bbce1ce29ddb89");
    private final static String KEY_BACKUP_MRENCLAVE = "45627094b2ea4a66f4cf0b182858a8dcf4b8479122c3820fe7fd0551a6d4cf5c";
    private final static String FALLBACK_KEY_BACKUP_ENCLAVE_NAME = "0cedba03535b41b67729ce9924185f831d7767928a1d1689acb689bc079c375f";
    private final static byte[] FALLBACK_KEY_BACKUP_SERVICE_ID = Hex.decode(
            "187d2739d22be65e74b65f0055e74d31310e4267e5fac2b1246cc8beba81af39");
    private final static String FALLBACK_KEY_BACKUP_MRENCLAVE = "ee19f1965b1eefa3dc4204eb70c04f397755f771b8c1909d080c04dad2a6a9ba";

    private final static String URL = "https://chat.signal.org";
    private final static String CDN_URL = "https://cdn.signal.org";
    private final static String CDN2_URL = "https://cdn2.signal.org";
    private final static String SIGNAL_KEY_BACKUP_URL = "https://api.backup.signal.org";
    private final static String STORAGE_URL = "https://storage.signal.org";
    private final static String SIGNAL_CDSI_URL = "https://cdsi.signal.org";
    private final static String SIGNAL_SVR2_URL = "https://svr2.signal.org";
    private final static TrustStore TRUST_STORE = new WhisperTrustStore();

    private final static Optional<Dns> dns = Optional.empty();
    private final static Optional<SignalProxy> proxy = Optional.empty();

    private final static byte[] zkGroupServerPublicParams = Base64.getDecoder()
            .decode("AMhf5ywVwITZMsff/eCyudZx9JDmkkkbV6PInzG4p8x3VqVJSFiMvnvlEKWuRob/1eaIetR31IYeAbm0NdOuHH8Qi+Rexi1wLlpzIo1gstHWBfZzy1+qHRV5A4TqPp15YzBPm0WSggW6PbSn+F4lf57VCnHF7p8SvzAA2ZZJPYJURt8X7bbg+H3i+PEjH9DXItNEqs2sNcug37xZQDLm7X36nOoGPs54XsEGzPdEV+itQNGUFEjY6X9Uv+Acuks7NpyGvCoKxGwgKgE5XyJ+nNKlyHHOLb6N1NuHyBrZrgtY/JYJHRooo5CEqYKBqdFnmbTVGEkCvJKxLnjwKWf+fEPoWeQFj5ObDjcKMZf2Jm2Ae69x+ikU5gBXsRmoF94GXTLfN0/vLt98KDPnxwAQL9j5V1jGOY8jQl6MLxEs56cwXN0dqCnImzVH3TZT1cJ8SW1BRX6qIVxEzjsSGx3yxF3suAilPMqGRp4ffyopjMD1JXiKR2RwLKzizUe5e8XyGOy9fplzhw3jVzTRyUZTRSZKkMLWcQ/gv0E4aONNqs4P");
    private final static byte[] genericServerPublicParams = Base64.getDecoder()
            .decode("AByD873dTilmOSG0TjKrvpeaKEsUmIO8Vx9BeMmftwUs9v7ikPwM8P3OHyT0+X3EUMZrSe9VUp26Wai51Q9I8mdk0hX/yo7CeFGJyzoOqn8e/i4Ygbn5HoAyXJx5eXfIbqpc0bIxzju4H/HOQeOpt6h742qii5u/cbwOhFZCsMIbElZTaeU+BWMBQiZHIGHT5IE0qCordQKZ5iPZom0HeFa8Yq0ShuEyAl0WINBiY6xE3H/9WnvzXBbMuuk//eRxXgzO8ieCeK8FwQNxbfXqZm6Ro1cMhCOF3u7xoX83QhpN");

    static SignalServiceConfiguration createDefaultServiceConfiguration(
            final List<Interceptor> interceptors
    ) {
        return new SignalServiceConfiguration(new SignalServiceUrl[]{new SignalServiceUrl(URL, TRUST_STORE)},
                Map.of(0,
                        new SignalCdnUrl[]{new SignalCdnUrl(CDN_URL, TRUST_STORE)},
                        2,
                        new SignalCdnUrl[]{new SignalCdnUrl(CDN2_URL, TRUST_STORE)}),
                new SignalKeyBackupServiceUrl[]{new SignalKeyBackupServiceUrl(SIGNAL_KEY_BACKUP_URL, TRUST_STORE)},
                new SignalStorageUrl[]{new SignalStorageUrl(STORAGE_URL, TRUST_STORE)},
                new SignalCdsiUrl[]{new SignalCdsiUrl(SIGNAL_CDSI_URL, TRUST_STORE)},
                new SignalSvr2Url[]{new SignalSvr2Url(SIGNAL_SVR2_URL, TRUST_STORE, null, null)},
                interceptors,
                dns,
                proxy,
                zkGroupServerPublicParams,
                genericServerPublicParams);
    }

    static ECPublicKey getUnidentifiedSenderTrustRoot() {
        try {
            return Curve.decodePoint(UNIDENTIFIED_SENDER_TRUST_ROOT, 0);
        } catch (InvalidKeyException e) {
            throw new AssertionError(e);
        }
    }

    static KeyBackupConfig createKeyBackupConfig() {
        return new KeyBackupConfig(KEY_BACKUP_ENCLAVE_NAME, KEY_BACKUP_SERVICE_ID, KEY_BACKUP_MRENCLAVE);
    }

    static Collection<KeyBackupConfig> createFallbackKeyBackupConfigs() {
        return List.of(new KeyBackupConfig(FALLBACK_KEY_BACKUP_ENCLAVE_NAME,
                FALLBACK_KEY_BACKUP_SERVICE_ID,
                FALLBACK_KEY_BACKUP_MRENCLAVE));
    }

    static String getCdsiMrenclave() {
        return CDSI_MRENCLAVE;
    }

    static String getSvr2Mrenclave() {
        return SVR2_MRENCLAVE;
    }

    private LiveConfig() {
    }
}
