const API_BASE_URL = "https://k4a304.p.ssafy.io/api-auth";
const ACCESS_TOKEN = "accessToken";
const OAUTH2_REDIRECT_URI = "https://k4a304.p.ssafy.io/user-page";
const GOOGLE_AUTH_URL =
  API_BASE_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;

export { API_BASE_URL, ACCESS_TOKEN, OAUTH2_REDIRECT_URI, GOOGLE_AUTH_URL };
