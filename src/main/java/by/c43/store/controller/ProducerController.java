package by.c43.store.controller;

import by.c43.store.dto.addressDTO.AllArgAddressDTO;
import by.c43.store.dto.producerDTO.*;
import by.c43.store.dto.telephonesDTO.NumberTelDTO;
import by.c43.store.entity.Producer;
import by.c43.store.entity.Telephone;
import by.c43.store.entity.User;
import by.c43.store.service.ProducerService;
import by.c43.store.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/producer")
@AllArgsConstructor
public class ProducerController {

    private final ProducerService producerService;


    @GetMapping("/reg")
    public String registration(@ModelAttribute("newProducer")AllArgsProducerDTO allArgsProducerDTO){
        return "regProducer";
    }

    @PostMapping("/reg")
    public String registration(@Valid @ModelAttribute("newProducer") AllArgsProducerDTO allArgsProducerDTO,
                               BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            Producer producer = ConverterOfDTO.getAllArgsProducerDTO(allArgsProducerDTO);
            if(producerService.saveProducer(producer)){
                model.addAttribute("message", "Registration successfully passed!");
            }else model.addAttribute("message", "Registration failed!");
        }
        return "regProducer";
    }


    @GetMapping("/auth")
    public String authorization(@ModelAttribute("oldProducer") EmailPasswordProducerDTO emailPasswordProducerDTO){
        return "authProducer";
    }



    @PostMapping ("/auth")
    public String authorization(@Valid @ModelAttribute("oldProducer") EmailPasswordProducerDTO producerDTO,
                                BindingResult bindingResult, HttpSession httpSession, Model model){
        if(!bindingResult.hasErrors()){
            Producer producer = ConverterOfDTO.getEmailPasswordProducerDTO(producerDTO);
            Optional<Producer> producerOptional = producerService.getProductByEmail(producer.getEmail());
            if(producerOptional.isPresent()){
                httpSession.setAttribute("producer", producerOptional.get());
                model.addAttribute("message", "Authorization successfully passed!");
            }else model.addAttribute("message", "Authorization failed!");
        }
        return "authProducer";
    }


    @GetMapping("/updateName")
    public String updateName(@ModelAttribute("producerName") ProducerNameDTO producerNameDTO){
        return "udpateNameProducer";
    }


    @PostMapping ("/updateName")
    public String updateName(@Valid @ModelAttribute("producerName") ProducerNameDTO producerNameDTO, BindingResult bindingResult,
                             Model model, HttpSession httpSession){
        if(!bindingResult.hasErrors()){
            Producer producer =(Producer) httpSession.getAttribute("producer");
            if(producerService.updateName(producer, producerNameDTO.getName())){
                producer.setName(producerNameDTO.getName());
                model.addAttribute("message", "Update name successfully passed");
            }else model.addAttribute("message", "Update name failed!");
        }
        return "udpateNameProducer";
    }

    @GetMapping("/updateEmail")
    public String updateEmail(@ModelAttribute("producerEmail")ProducerEmailDTO producerEmailDTO){
        return "udpateEmailProducer";
    }


    @PostMapping ("/updateEmail")
    public String updateEmail(@Valid @ModelAttribute("producerEmail") ProducerEmailDTO producerEmailDTO, BindingResult bindingResult,
                             Model model, HttpSession httpSession){
        if(!bindingResult.hasErrors()){
            Producer producer =(Producer) httpSession.getAttribute("producer");
            if(producerService.updateEmail(producer, producerEmailDTO.getEmail())){
                producer.setEmail(producerEmailDTO.getEmail());
                model.addAttribute("message", "Update email successfully passed");
            }else model.addAttribute("message", "Update email failed!");
        }
        return "udpateEmailProducer";
    }


    @GetMapping("/updatePassword")
    public String updatePassword(@ModelAttribute("producerPassword")ProducerPasswordDTO passwordDTO){
        return "udpatePasswordProducer";
    }


    @PostMapping ("/updatePassword")
    public String updatePassword(@Valid @ModelAttribute("producerPassword") ProducerPasswordDTO passwordDTO, BindingResult bindingResult,
                              Model model, HttpSession httpSession){
        if(!bindingResult.hasErrors()){
            Producer producer =(Producer) httpSession.getAttribute("producer");
            if(producerService.updatePassword(producer, passwordDTO.getNewPassword(), passwordDTO.getOldPassword())){
                producer.setPassword(passwordDTO.getNewPassword());
                model.addAttribute("message", "Update password successfully passed");
            }else model.addAttribute("message", "Update password failed!");
        }
        return "udpatePasswordProducer";
    }

    @GetMapping("/updatePicture")
    public String updatePicture(@ModelAttribute("producerPicture") ProducerPictureDTO producerPictureDTO){
        return "udpatePictureProducer";
    }


    @PostMapping ("/updatePicture")
    public String updatePicture(@Valid @ModelAttribute("producerPicture") ProducerPictureDTO pictureDTO, BindingResult bindingResult,
                                 Model model, HttpSession httpSession){
        if(!bindingResult.hasErrors()){
            Producer producer =(Producer) httpSession.getAttribute("producer");
            if(producerService.updatePicture(producer, pictureDTO.getPicture())){
                producer.setPicture(pictureDTO.getPicture());
                model.addAttribute("message", "Update picture successfully passed");
            }else model.addAttribute("message", "Update picture failed!");
        }
        return "udpatePictureProduce";
    }


    @GetMapping("/updateDescription")
    public String updateDescription(@ModelAttribute("producerDescription") ProducerDescriptionDTO descriptionDTO){
        return "udpateDescriptionProducer";
    }


    @PostMapping ("/updateDescription")
    public String updateDescription(@Valid @ModelAttribute("producerDescription") ProducerDescriptionDTO descriptionDTO, BindingResult bindingResult,
                                Model model, HttpSession httpSession){
        if(!bindingResult.hasErrors()){
            Producer producer =(Producer) httpSession.getAttribute("producer");
            if(producerService.updateDescription(producer, descriptionDTO.getDescription())){
                producer.setDescription(descriptionDTO.getDescription());
                model.addAttribute("message", "Update description successfully passed");
            }else model.addAttribute("message", "Update description failed!");
        }
        return "udpateDescriptionProduce";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id, HttpSession httpSession, Model model){
        if(producerService.deleteProducer(id, (User) httpSession.getAttribute("user"))){
            model.addAttribute("message", "Producer successfully deleted");
        }else model.addAttribute("message", "Delete of producer failed");
        return "";
    }

    @GetMapping("/addTelephone")
    public String addTelephone(@ModelAttribute("newTelephone")NumberTelDTO numberTelDTO){
        return "addTelephoneProducer";
    }


    @PostMapping ("/addTelephone")
    public String addTelephone(@Valid @ModelAttribute("newTelephone") NumberTelDTO numberTelDTO, BindingResult bindingResult,
                                    Model model, HttpSession httpSession){
        if(!bindingResult.hasErrors()){
            Producer producer =(Producer) httpSession.getAttribute("producer");
            if(producerService.addTelephone(producer, ConverterOfDTO.getTelDTO(numberTelDTO))){
                producer.getTelephones().add(ConverterOfDTO.getTelDTO(numberTelDTO));
                model.addAttribute("message", "Telephone successfully added");
            }else model.addAttribute("message", "The adding of the telephone failed!");
        }
        return "addTelephoneProducer";
    }


    @GetMapping("/updateTelephone")
    public String updateTelephone(@ModelAttribute("telephone")NumberTelDTO numberTelDTO){
        return "updateTelephoneProducer";
    }


    @PostMapping ("/updateTelephone")
    public String updateTelephone(@Valid @ModelAttribute("telephone") NumberTelDTO numberTelDTO, BindingResult bindingResult,
                                  Model model, HttpSession httpSession){
        if(!bindingResult.hasErrors()){
            Producer producer =(Producer) httpSession.getAttribute("producer");
            Telephone telephone = ConverterOfDTO.getTelDTO(numberTelDTO);
            if(producerService.updateTelephone(producer, telephone)){
                producer.getTelephones().set(producer.getTelephones().indexOf(telephone), telephone );
                model.addAttribute("message", "Telephone successfully updated");
            }else model.addAttribute("message", "The update of the telephone failed!");
        }
        return "updateTelephoneProducer";
    }


    @GetMapping("/updateAddress")
    public String updateAddress(@ModelAttribute("address")AllArgAddressDTO allArgAddressDTO){
        return "updateAddressProducer";
    }


    @PostMapping ("/updateAddress")
    public String updateAddress(@Valid @ModelAttribute("address") AllArgAddressDTO addressDTO, BindingResult bindingResult,
                                  Model model, HttpSession httpSession){
        if(!bindingResult.hasErrors()){
            Producer producer =(Producer) httpSession.getAttribute("producer");
            if(producerService.updateAddress(producer, ConverterOfDTO.getAllArgAddressDTO(addressDTO))){
                producer.setAddress(ConverterOfDTO.getAllArgAddressDTO(addressDTO));
                model.addAttribute("message", "Address successfully updated");
            }else model.addAttribute("message", "The update of the address failed!");
        }
        return "updateAddressProducer";
    }

    @PostMapping("/logOut")
    public String logOut(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }



}