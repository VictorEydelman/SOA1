<template>
  <div class="p-6 max-w-11/12 mx-auto">
    <div class="flex items-center justify-between mb-4">
      <h1 class="text-2xl font-semibold">Routes</h1>
    </div>
    <div class="flex justify-between items-center mb-6">
      <NuxtLink to="/form-route">
        <UButton color="primary" icon="i-heroicons-plus">
          Добавить Route
        </UButton>
      </NuxtLink>
    </div>
    <RoutesTable />
    <div class="flex items-center justify-between mb-4 pt-8">
      <h1 class="text-2xl font-semibold">Additional Operations</h1>
    </div>
    <AdditionalOperations />
    <AdditionalTables />
  </div>
</template>
<script setup lang="ts">
import FormRoute from "~/pages/form-route.vue";
import RoutesTable from "~/pages/components/routes-table.vue";
import AdditionalOperations from "~/pages/components/additional-operations.vue";
import AdditionalTables from "~/pages/components/additional-tables.vue";
</script>