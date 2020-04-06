package edu.born.overseer.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import edu.born.overseer.model.CompanyType;
import edu.born.overseer.model.CounterpartyType;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.model.ResultType;

public class ConvertTypesUtil {

    private ConvertTypesUtil() {
    }

    private static BiMap<CompanyType, String> companyTypes = new ImmutableBiMap.Builder<CompanyType, String>()
            .put(CompanyType.OUR, "Наше")
            .put(CompanyType.CUSTOMER, "Заказчик")
            .put(CompanyType.OTHER, "Остальные")
            .build();

    private static BiMap<ResultType, String> resultTypes = new ImmutableBiMap.Builder<ResultType, String>()
            .put(ResultType.COMPLETED, "Выполнено")
            .put(ResultType.NOT_COMPLETED, "Не выполнено")
            .put(ResultType.PARTIALLY_COMPLETED, "Частично выполнено")
            .build();

    private static BiMap<ReliabilityType, String> reliabilityTypes = new ImmutableBiMap.Builder<ReliabilityType, String>()
            .put(ReliabilityType.LOW, "Низкий")
            .put(ReliabilityType.MIDDLE, "Средний")
            .put(ReliabilityType.HIGH, "Высокий")
            .build();

    private static BiMap<CounterpartyType, String> counterpartyTypes = new ImmutableBiMap.Builder<CounterpartyType, String>()
            .put(CounterpartyType.COMPANY, "Компания")
            .put(CounterpartyType.EMPLOYEE, "Сотрудник")
            .build();

    public static String convertCompanyTypeToString(CompanyType type) {
        return companyTypes.get(type);
    }

    public static CompanyType convertStringToCompanyType(String type) {
        return companyTypes.inverse().get(type);
    }

    public static String convertResultTypeToString(ResultType type) {
        return resultTypes.get(type);
    }

    public static ResultType convertStringToResultType(String type) {
        return resultTypes.inverse().get(type);
    }

    public static String convertReliabilityTypeToString(ReliabilityType type) {
        return reliabilityTypes.get(type);
    }

    public static ReliabilityType convertStringToReliabilityType(String type) {
        return reliabilityTypes.inverse().get(type);
    }

    public static String convertCounterpartyTypeToString(CounterpartyType type) {
        return counterpartyTypes.get(type);
    }

    public static CounterpartyType convertStringToCounterpartyType(String type) {
        return counterpartyTypes.inverse().get(type);
    }

}
