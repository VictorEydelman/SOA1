<script setup lang="ts">
import {ref, onMounted} from 'vue'
import xml2js from 'xml2js'

const BASE_URL = 'http://localhost:8080/api/v1'

function normalizeLocation(location: Location): Location {
  return {
    id: +location.id,
    x: +location.x,
    y: +location.y,
    z: +location.z,
    name: location.name
  }
}

function normalizeRoute(route: Route): Route {
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

async function parseXmlRoutes(xmlText: string): Promise<Route[]> {
  const obj = await xml2js.parseStringPromise(xmlText, {
    explicitArray: false
  });
  if (!obj.routes?.route) return [];
  if (!(obj.routes.route instanceof Array)) return [obj.routes.route];
  return obj.routes.route;
}

const { data, error, pending } = await useFetch(BASE_URL + '/routes', {
  method: 'get',
  lazy: true,
});

const routes = ref<Route[]>([]);

if (data) {
  let rawRoutes = await parseXmlRoutes(data.value);
  routes.value = rawRoutes.map(normalizeRoute);
}

// onMounted(() => {
//   console.log("mounted")
// })
</script>

<template>
  <div class="bg-white/5 rounded-lg shadow-sm overflow-hidden">
    <table class="min-w-full table-auto">
      <thead class="bg-gray-50">
      <tr>
        <th class="text-left px-4 py-3">ID</th>
        <th class="text-left px-4 py-3">Name</th>
        <th class="text-left px-4 py-3">Coords</th>
        <th class="text-left px-4 py-3">Created</th>
        <th class="text-left px-4 py-3">From ID</th>
        <th class="text-left px-4 py-3">From Name</th>
        <th class="text-left px-4 py-3">From Coords</th>
        <th class="text-left px-4 py-3">To ID</th>
        <th class="text-left px-4 py-3">To Name</th>
        <th class="text-left px-4 py-3">To Coords</th>
        <th class="text-left px-4 py-3">Distance</th>
      </tr>
      </thead>
      <tbody>
      <tr v-if="pending">
        <td :colspan="11" class="px-4 py-6 text-center">Loading...</td>
      </tr>
      <tr v-else-if="error">
        <td :colspan="11" class="px-4 py-6 text-center text-red-500">{{ error }}</td>
      </tr>
      <tr v-else-if="!routes.length">
        <td :colspan="11" class="px-4 py-6 text-center text-gray-400">No routes found.</td>
      </tr>
      <tr v-else v-for="route in routes" :key="route.id" class="border-t last:border-b hover:bg-gray-50">
        <td class="px-4 py-3">{{ route.id }}</td>
        <td class="px-4 py-3">{{ route.name }}</td>
        <td class="px-4 py-3">{{ `${route.coordinates.x}, ${route.coordinates.y}` }}</td>
        <td class="px-4 py-3">{{ route.creationDate.toLocaleString() }}</td>
        <td class="px-4 py-3">{{ route.from?.id }}</td>
        <td class="px-4 py-3">{{ route.from?.name }}</td>
        <td class="px-4 py-3">{{ route.from ? `${route.from.x}, ${route.from.y}, ${route.from.z}` : '' }}</td>
        <td class="px-4 py-3">{{ route.to?.id }}</td>
        <td class="px-4 py-3">{{ route.to?.name }}</td>
        <td class="px-4 py-3">{{ `${route.to.x}, ${route.to.y}, ${route.to.z}` }}</td>
        <td class="px-4 py-3">{{ route.distance }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
