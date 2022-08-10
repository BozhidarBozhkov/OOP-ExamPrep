package fairyShop.core;

import fairyShop.models.Happy;
import fairyShop.models.Helper;
import fairyShop.models.Sleepy;
import fairyShop.models.Instrument;
import fairyShop.models.InstrumentImpl;
import fairyShop.models.Present;
import fairyShop.models.PresentImpl;
import fairyShop.models.ShopImpl;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private HelperRepository<Helper> helperRepository = new HelperRepository<>();
    private PresentRepository<Present> presentRepository = new PresentRepository<>();


    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);

        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> eligibleHelper = helperRepository.getModels().stream()
                .filter(h -> h.getEnergy() > 50)
                .collect(Collectors.toList());
        if (eligibleHelper.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        int brokenInstruments = 0;
        Present present = presentRepository.findByName(presentName);
        ShopImpl shop = new ShopImpl();
        for (Helper helper : eligibleHelper) {
            shop.craft(present, helper);
            brokenInstruments += helper.getInstruments().stream().filter(Instrument::isBroken).count();
            if (present.isDone()) {
                break;
            }
        }

        String output = present.isDone() ? "done" : "not done";

        return String.format(PRESENT_DONE + COUNT_BROKEN_INSTRUMENTS, presentName, output, brokenInstruments);
    }

    @Override
    public String report() {
        int size = (int) presentRepository.getModels().stream().filter(Present::isDone).count();
        List<String> collect = helperRepository.getModels().stream().map(helper -> String.format("Name: %s%n" +
                        "Energy: %d%n" +
                        "Instruments: %d not broken left%n", helper.getName(), helper.getEnergy(),
                (int) helper.getInstruments().stream().filter(instrument -> !instrument.isBroken()).count())).collect(Collectors.toList());
        return String.format("%d presents are done!%n", size) + String.format("Helpers info:%n") + String.join("", collect).trim();
    }
}
