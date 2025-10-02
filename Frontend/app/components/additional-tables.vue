<script setup lang="ts">
import type {TableColumn} from '@nuxt/ui'
import {ref} from 'vue'
import {normalizeRoute, parseXmlRoutes} from "~/utils/routes";

const prefixInput = ref<string>('');
const prefix = computed(() => prefixInput.value || 'undefined');

const {data: prefixData, error: prefixError} = useFetch(`${BASE_URL}/routes/name/starts-with`, {
  method: 'get',
  lazy: true,
  query: {prefix},
  transform: async (data) => data && (await parseXmlRoutes(data)).map(normalizeRoute),
});

const idFrom = ref<number>();
const idTo = ref<number>();
const orderBy = ref<string>('');


const {data: locationsData, error: locationsError} = useFetch(
    () => `${SECOND_URL}/routes/${idFrom.value ?? 0}/${idTo.value ?? 0}/${orderBy.value || 'id'}`, {
  method: 'get',
  lazy: true,
  transform: async (data) => data && (await parseXmlRoutes(data)).map(normalizeRoute),
});


const columns: TableColumn<Route>[] = [
  {
    header: "ID",
    accessorKey: "id",
  }, {
    header: "Name",
    accessorKey: "name",
  }, {
    header: "Coords",
    id: "coordinates",
    accessorFn: route => `${route.coordinates.x}, ${route.coordinates.y}`,
  }, {
    header: "Created",
    id: "creationDate",
    accessorFn: route => route.creationDate.toLocaleString(),
  }, {
    header: "From ID",
    id: 'from',
    accessorKey: 'from.id'
  }, {
    header: "From Name",
    accessorKey: 'from.name'
  }, {
    header: "From Coords",
    id: "from.coords",
    accessorFn: route => route.from && `${route.from.x}, ${route.from.y}, ${route.from.z}`
  }, {
    header: "To ID",
    id: 'to',
    accessorKey: 'to.id'
  }, {
    header: "To Name",
    accessorKey: 'to.name'
  }, {
    header: "To Coords",
    id: "to.coords",
    accessorFn: route => `${route.to.x}, ${route.to.y}, ${route.to.z}`
  }, {
    header: "Distance",
    accessorKey: 'distance'
  }
]

columns.forEach(col => {
  col.meta = {
    class: {
      th: "text-left px-4 py-3",
      td: "border-t px-4 py-3 text-white text-xs",
    }
  }
});

</script>

<template>
  <div class="bg-white/5 rounded-lg shadow-sm overflow-hidden">
    <div class="p-3">
      <p class="text-lg pb-2">Routes with name starts with:</p>
      <UInput
          placeholder="prefix" orientation="vertical"
          class="w-50 pb-2 pr-2"
          v-model="prefixInput"/>
    </div>
    <UTable
        :columns="columns" :data="prefixData"
        class="min-w-full table-auto"
    />
    <div v-if="prefixError">
      <div class="text-center text-red-500">{{ prefixError }}</div>
    </div>
  </div>
  <div class="bg-white/5 rounded-lg shadow-sm overflow-hidden mt-10">
    <div class="p-3">
      <p class="text-lg pb-2">Routes between locations and order by:</p>
      <UInputNumber
          placeholder="id-from" orientation="vertical"
          class="w-50 pb-2 pr-2"
          v-model="idFrom"/>
      <UInputNumber
          placeholder="id-to" orientation="vertical"
          class="w-50 pb-2 pr-2"
          v-model="idTo"/>
      <UInput
          placeholder="order-by" orientation="vertical"
          class="w-50 pb-2 pr-2"
          v-model="orderBy"/>
    </div>
    <UTable
        :columns="columns" :data="locationsData"
        class="min-w-full table-auto"
    />
    <div v-if="locationsError">
      <div class="text-center text-red-500">{{ locationsError }}</div>
    </div>
  </div>
</template>
