export {};

declare global {

    interface Coordinates {
        id: number;
        x: number;
        y: number;
    }

    interface Location {
        id: number;
        x: number;
        y: number;
        z: number;
        name?: string;
    }

    interface Route {
        id: number;
        name: string;
        coordinates: Coordinates;
        creationDate: Date;
        from?: Location;
        to: Location;
        distance?: number;
    }

}
