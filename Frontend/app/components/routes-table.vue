<script setup lang="ts">
import type {TableColumn} from '@nuxt/ui'
import {UButton} from '#components'
import {ref} from 'vue'
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

const sorting = ref({});

const filterParams = ['id', 'name', 'distance', 'coordinates-x', 'coordinates-y',
  'from-name', 'from-x', 'from-y', 'from-z', 'to-name', 'to-x', 'to-y', 'to-z'];
const filterValues = ref({});

const page = ref(1);
const size = ref(10);

const query = computed(() => ({
  page: page.value - 1,
  size: size.value,
  sort: Object.entries(sorting.value)
      .filter(([, v]) => v)
      .map(([k, v]) => `${k}:${v}`)
      .join(',') || undefined,
  ...filterValues.value
}));

const {data, error, pending, refresh} = useFetch(`${BASE_URL}/routes`, {
  method: 'get',
  lazy: true,
  transform: async (data) => data && (await parseXmlRoutes(data)).map(normalizeRoute),
  query
});

const deleteRoute = (id: number) => {
  $fetch(`${BASE_URL}/routes/${id}`, {
    method: 'delete'
  }).then(refresh).catch(err => error.value = err);
};

const columns: TableColumn<Route>[] = [
  {
    name: "ID",
    accessorKey: "id",
    sortable: true
  }, {
    name: "Name",
    accessorKey: "name",
    sortable: true
  }, {
    name: "Coords",
    id: "coordinates",
    accessorFn: route => `${route.coordinates.x}, ${route.coordinates.y}`,
    sortable: true
  }, {
    name: "Created",
    id: "creationDate",
    accessorFn: route => route.creationDate.toLocaleString(),
    sortable: true
  }, {
    name: "From ID",
    id: 'from',
    accessorKey: 'from.id',
    sortable: true
  }, {
    name: "From Name",
    accessorKey: 'from.name'
  }, {
    name: "From Coords",
    id: "from.coords",
    accessorFn: route => route.from && `${route.from.x}, ${route.from.y}, ${route.from.z}`
  }, {
    name: "To ID",
    id: 'to',
    accessorKey: 'to.id',
    sortable: true
  }, {
    name: "To Name",
    accessorKey: 'to.name'
  }, {
    name: "To Coords",
    id: "to.coords",
    accessorFn: route => `${route.to.x}, ${route.to.y}, ${route.to.z}`
  }, {
    name: "Distance",
    accessorKey: 'distance',
    sortable: true
  }, {
    name: " ",
    cell: props => h(UButton, {
      color: 'error', variant: 'outline',
      label: 'Delete',
      onClick: () => deleteRoute(props.row.getAllCells()[0].getValue())
    })
  }
]

columns.forEach(col => {
  col.meta = {
    class: {
      th: "text-left px-4 py-3",
      td: "border-t px-4 py-3 text-white text-xs",
    }
  }
  col.header = col.sortable ? ({column}) => {
    const state = sorting.value[column.id];
    return h(UButton, {
      color: 'neutral',
      variant: 'ghost',
      label: col.name,
      icon: state ? (state === 'asc'
              ? 'i-lucide-arrow-up-narrow-wide'
              : 'i-lucide-arrow-down-wide-narrow')
          : 'i-lucide-arrow-up-down',
      class: '-mx-2.5',
      onClick: () => sorting.value[column.id] = state ? (state === 'asc' ? 'desc' : null) : 'asc'
    });
  } : col.name
});

</script>

<template>
  <div class="w-full">
    <UInput
        v-for="(param, index) in filterParams"
        :key="index"
        :placeholder="param"
        class="max-w-22 w-fit pb-2 pr-2"
        :model-value="filterValues[param]"
        @update:model-value="filterValues[param] = $event != '' ? $event : undefined"
    />
  </div>
  <div class="bg-white/5 rounded-lg shadow-sm overflow-hidden">
    <UTable
        :columns="columns" :data="data"
        class="min-w-full table-auto"
    />
    <div v-if="error">
      <div class="text-center text-red-500">{{ error }}</div>
    </div>
    <UPagination
        class="flex justify-center border-t border-default pt-4 pb-4"
        :total="100" :items-per-page="1"
        v-model:page="page"
    />
  </div>
</template>
