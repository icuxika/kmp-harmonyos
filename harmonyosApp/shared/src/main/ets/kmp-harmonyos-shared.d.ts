type Nullable<T> = T | null | undefined
export declare class ApiExecutorJs {
    constructor();
    execute(callback: (p0: string) => void): void;
    static get Companion(): {
    };
}
export declare class Greeting {
    constructor();
    greet(): string;
}