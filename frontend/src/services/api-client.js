import axios from "axios";

//BASE URL für Spring Boot Backend
const API_BASE_URL = "http://localhost:8080/api";

//Axios Instance mit Basic Konfigurationen
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000, //10 Sek timeout
  headers: {
    "Content-Type": "application/json",
  },
});

// ===================================
// REQUEST INTERCEPTOR
// ===================================
apiClient.interceptors.request.use(
  (config) => {
    //Token aus LocalStroage
    const token = localStorage.getItem("authToken");

    //Wenn Token existiert zur Auth hinzufügen
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      console.log("Token wird mitgeschickt");
    }

    return config;
  },
  (error) => {
    console.error("Request Interceptor Error", error);
    return Promise.reject(error);
  }
);

// ===================================
// RESPONSE INTERCEPTOR
// ===================================
apiClient.interceptors.response.use(
  (response) => {
    //Succsess Response
    return response;
  },
  (error) => {
    //Error Response
    if (error.response) {
      const status = error.response.status;

      //401 Unauthorized (TOken ungültig/abgelaufen)
      if (status === 401) {
        console.log("Token ungültig");

        //Token aus LocalStorage löschen
        localStorage.removeItem("authToken");

        //Zu Login Page redirect
        window.location.href = "/login";
      }

      //403 Forbidden (Keine Berechtigung)
      if (status === 403) {
        console.log("Keine Berechtigung für diese Aktion");
      }
    }

    return Promise.reject(error);
  }
);
export default apiClient;
