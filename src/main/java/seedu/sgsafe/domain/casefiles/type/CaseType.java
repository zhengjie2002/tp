package seedu.sgsafe.domain.casefiles.type;

/**
 * Represents the broader types of a case,
 * used to group case categories under classifications
 * such as {@code FINANCIAL}, {@code TRAFFIC}, or {@code VIOLENT}.
 */
public enum CaseType {
    VIOLENT,
    PROPERTY,
    FINANCIAL,
    SEXUAL,
    TRAFFIC,
    OTHERS
}
