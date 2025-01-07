import android.app.Application
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.HostnameVerifier

class SSLDiable : Application() {
    override fun onCreate() {
        super.onCreate()

        // Llamar a la función que desactiva la validación de SSL
        disableSSLValidation()
    }

    private fun disableSSLValidation() {
        HttpsURLConnection.setDefaultHostnameVerifier(HostnameVerifier { _, _ -> true })
    }
}

