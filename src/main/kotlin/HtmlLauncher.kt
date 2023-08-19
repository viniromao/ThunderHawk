import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.gwt.GwtApplication
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration


class HtmlLauncher : GwtApplication() {
    override fun getConfig() = GwtApplicationConfiguration(480, 320)

    override fun createApplicationListener(): ApplicationListener = ThunderHawk()
}