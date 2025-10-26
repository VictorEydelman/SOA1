import xml2js from "xml2js";

const HOST = 'localhost'
export const BASE_URL = `https://${HOST}:5666/api/v1`
export const SECOND_URL = `https://${HOST}:24771/Service2-1.0/api/v1/navigator`

export function normalizeLocation(location: Location): Location {
    return {
        id: +location.id,
        x: +location.x,
        y: +location.y,
        z: +location.z,
        name: location.name
    }
}

export function normalizeRoute(route: Route): Route {
    return {
        id: +route.id,
        name: route.name,
        coordinates: {
            id: +route.coordinates.id,
            x: +route.coordinates.x,
            y: +route.coordinates.y
        },
        creationDate: new Date(route.creationDate),
        from: route.from && normalizeLocation(route.from),
        to: normalizeLocation(route.to),
        distance: route.distance && +route.distance
    }
}

export function parseXml(xmlText: string) {
    return xml2js.parseStringPromise(xmlText, {
        explicitArray: false
    });
}

export async function parseXmlRoutes(xmlText: string): Promise<Route[]> {
    const obj = await parseXml(xmlText);
    if (!obj.routes?.route) return [];
    if (!(obj.routes.route instanceof Array)) return [obj.routes.route];
    return obj.routes.route;
}
