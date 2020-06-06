package com.example.giswisatacamping;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

class JsonArrayRequest extends Request<Object> {
    @Override
    protected Response<Object> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(Object response) {

    }
}
