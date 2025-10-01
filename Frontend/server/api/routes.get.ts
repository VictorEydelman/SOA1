const MOCK_XML = `<?xml version="1.0" encoding="UTF-8"?>
<routes>
  <route>
    <id>1</id>
    <name>Main route</name>
    <coordinates>
    <id>1</id>
      <x>100</x>
      <y>12.5</y>
    </coordinates>
    <creationDate>2025-09-03T12:00:00Z</creationDate>
    <from>
    <id>4</id>
      <x>1</x>
      <y>2</y>
      <z>3</z>
      <name>Origin</name>
    </from>
    <to>
    <id>5</id>
      <x>10</x>
      <y>20</y>
      <z>30</z>
      <name>Dest</name>
    </to>
    <distance>42</distance>
  </route>
  <route>
    <id>1</id>
    <name>Main route</name>
    <coordinates>
    <id>1</id>
      <x>100</x>
      <y>12.5</y>
    </coordinates>
    <creationDate>2025-09-03T12:00:00Z</creationDate>
    <from>
    <id>1</id>
      <x>1</x>
      <y>2</y>
      <z>3</z>
      <name>Origin</name>
    </from>
    <to>
    <id>1</id>
      <x>10</x>
      <y>20</y>
      <z>30</z>
      <name>Dest</name>
    </to>
    <distance>42</distance>
  </route>

</routes>`

export default defineEventHandler(async (event) => {
    // return sendError(event, createError({
    //     statusCode: 500,
    //     statusMessage: 'q'
    // }))
    //await new Promise(r => setTimeout(r, 2000))
    return MOCK_XML
})
