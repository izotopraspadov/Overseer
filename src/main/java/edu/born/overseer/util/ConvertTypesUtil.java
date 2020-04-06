package edu.born.overseer.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import edu.born.overseer.model.CompanyType;
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

    public static String companyTypeToString(CompanyType type) {
        return companyTypes.get(type);
    }

    public static CompanyType stringToCompanyType(String type) {
        return companyTypes.inverse().get(type);
    }

    public static String resultTypeToString(ResultType type) {
        return resultTypes.get(type);
    }

    public static ResultType stringToResultType(String type) {
        return resultTypes.inverse().get(type);
    }

    public static String reliabilityTypeToString(ReliabilityType type) {
        return reliabilityTypes.get(type);
    }

    public static ReliabilityType stringToReliabilityType(String type) {
        return reliabilityTypes.inverse().get(type);
    }

}
