package com.nocountry.woco.service;

import com.nocountry.woco.model.request.ServiceRequest;
import com.nocountry.woco.model.response.ServiceResponse;

import java.util.List;

public interface IServiceService {

    List<ServiceResponse> getAllServices();
    ServiceResponse getServiceById(Long id);
    ServiceResponse addService(ServiceRequest service);
    ServiceResponse updateService(Long id, ServiceRequest serviceRequest);
    void deleteService(Long id);
    List<ServiceResponse> getServicesByPrice(Double price);
}
