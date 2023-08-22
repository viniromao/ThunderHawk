package co.neo32.deepdivetreasures.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import co.neo32.deepdivetreasures.DeepDiveTreasures;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Panel;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        return new GwtApplicationConfiguration(320 * 2, 224 * 2);
        // Fixed size application:
        //return new GwtApplicationConfiguration(480, 320);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new DeepDiveTreasures();
    }

//        @Override
//        public Preloader.PreloaderCallback getPreloaderCallback() {
//                return createPreloaderPanel(GWT.getHostPageBaseURL() + "preloadlogo.png");
//        }
//
//        @Override
//        protected void adjustMeterPanel(Panel meterPanel, Style meterStyle) {
//                meterPanel.setStyleName("gdx-meter");
//                meterPanel.addStyleName("nostripes");
//                meterStyle.setProperty("backgroundColor", "#000");
//                meterStyle.setProperty("backgroundImage", "none");
//        }

    @Override
    public Preloader.PreloaderCallback getPreloaderCallback() {
        // You can still use the default preloader panel, or just make custom changes
        return createPreloaderPanel("");
    }

    @Override
    protected void adjustMeterPanel(Panel meterPanel, Style meterStyle) {
        // Set the background color to black
        meterStyle.setProperty("backgroundColor", "#000000");

        // You may also want to hide the meter itself if you don't want to display loading progress
        meterStyle.setProperty("display", "none");
    }
}
