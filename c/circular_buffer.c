#include "stdio.h"
#include "stdint.h"
#include "circular_buffer.h"

uint16_t buffer_length (cbuffer_t* buffer)
{
	return ((buffer->write - buffer->read) & (buffer->size - 1));
}

cberror_t buffer_write(cbuffer_t* buffer, uint8_t element)
{
	if (buffer_length(buffer) == buffer->size - 1) {
		return eErrorBufferFull;
	}
	buffer->values[buffer->write] = element;
	buffer->write = (buffer->write + 1) & (buffer->size - 1);

	return eOK;
}

cberror_t buffer_read(cbuffer_t* buffer, uint8_t *element)
{
	if (buffer_length(buffer) == 0) {
		return eErrorBufferEmpty;
	}
	*element = buffer->values[buffer->read];
	buffer->read = (buffer->read + 1) & (buffer->size - 1);

	return eOK;
}

void buffer_init(cbuffer_t* buffer, uint8_t *elements, int size)
{
	buffer->values = elements;
	buffer->size = size;
	buffer->read = 0;
	buffer->write = 0;
}

int main(void)
{
	cbuffer_t buffer;
	uint8_t actuals[16];
	buffer_init(&buffer, (uint8_t *) &actuals, 16);

	uint16_t len = buffer_length(&buffer);
	printf ("buffer length: %d\n", len);

	buffer_write(&buffer, 'A');
	len = buffer_length(&buffer);
	printf ("buffer length: %d\n", len);

	uint8_t ch;
	printf ("val: %d\n", ch);
	buffer_read(&buffer, &ch);
	printf ("val: %d\n", ch);
	len = buffer_length(&buffer);
	printf ("buffer length: %d\n", len);

	return 0;
}
