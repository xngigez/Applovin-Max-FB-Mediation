package zggo.rewards;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements MaxAdViewAdListener {

    private MaxAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAppLovinSdk();
    }

    private void initAppLovinSdk() {
        // Please make sure to set the mediation provider value to "max" to ensure proper functionality
        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
                loadAppLovinBannerAd();
            }
        } );

        AppLovinSdk.getInstance(this).getSettings().setTestDeviceAdvertisingIds(Arrays.asList("c770953b-049f-423d-936c-2cd3f602cc22"));
    }

    private void loadAppLovinBannerAd() {
        adView = new MaxAdView( "c740ef5bec374962", this);
        adView.setListener(this);

        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = getResources().getDimensionPixelSize( R.dimen.banner_height );

        adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );

        // Set background or background color for banners to be fully functional
        adView.setBackgroundColor(Color.RED);

        ViewGroup rootView = findViewById( android.R.id.content );
        rootView.addView( adView );

        // Load the ad
        adView.loadAd();
    }

    @Override
    public void onAdExpanded(MaxAd ad) {

    }

    @Override
    public void onAdCollapsed(MaxAd ad) {

    }

    @Override
    public void onAdLoaded(MaxAd ad) {
        Log.i("message", "AppLovinBannerAd onAdLoaded");
    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {
        Log.i("message", "AppLovinBannerAd onAdLoadFailed: " + error.getMessage());
    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
        Log.i("message", "AppLovinBannerAd onAdDisplayFailed: " + error.getMessage());
    }
}