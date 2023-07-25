package com.nocountry.woco.service.impl;

import com.nocountry.woco.exception.NotFoundException;
import com.nocountry.woco.model.entity.Services;
import com.nocountry.woco.model.repository.ServiceRepository;
import com.nocountry.woco.model.request.ServiceRequest;
import com.nocountry.woco.model.response.ServiceResponse;
import com.nocountry.woco.service.IServiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceService implements IServiceService {

    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ServiceResponse> getAllServices() {
        List<Services> services = serviceRepository.findAll();
        return services.stream()
                .map(service -> modelMapper.map(service, ServiceResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        Optional<Services> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            return modelMapper.map(service, ServiceResponse.class);
        }
        else{
            throw new NotFoundException( "Service with id " + id + " not found");
        }

    }
    @Override
    public ServiceResponse addService(ServiceRequest serviceRequest) {
        Services service = modelMapper.map(serviceRequest, Services.class);
        return modelMapper.map(serviceRepository.save(service), ServiceResponse.class);
    }
    @Override
    public ServiceResponse updateService(Long id, ServiceRequest serviceRequest) {
        if( serviceRepository.findById(id).isPresent()){
            Services existingService = serviceRepository.findById(id).get();
            modelMapper.map(serviceRequest, existingService);
            return modelMapper.map(serviceRepository.save(existingService), ServiceResponse.class);
        }
        else{
            throw new NotFoundException( "Service with id " + id + " not found");
        }
    }
    @Override
    public void deleteService(Long id) {
        Services existingService = serviceRepository.findById(id).get();
        serviceRepository.delete(existingService);
    }
}
