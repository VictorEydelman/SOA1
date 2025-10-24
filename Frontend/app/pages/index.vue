<template>
  <div class="p-6 max-w-11/12 mx-auto">
    <div class="flex items-center justify-between mb-4">
      <h1 class="text-2xl font-semibold">Routes</h1>
    </div>
    <div class="flex justify-between items-center mb-6">
      <NuxtLink to="/form-route">
        <UButton color="primary" icon="i-heroicons-plus">
          Create Route
        </UButton>
      </NuxtLink>
    </div>
    <RoutesTable />
    <div class="flex items-center justify-between mb-4 pt-8">
      <h1 class="text-2xl font-semibold">Additional Operations</h1>
    </div>
    <AdditionalOperations />
    <div class="mb-6">
      <NuxtLink to="/form-route-between-locations">
        <UButton color="primary" icon="i-heroicons-plus">
          Add Route Between Locations
        </UButton>
      </NuxtLink>
    </div>
    <AdditionalTables />
  </div>
</template>
<script setup lang="ts">
</script>