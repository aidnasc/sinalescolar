#include <VirtualWire.h>
#define MAX_DATA 4

//Variavel Sirene/LED
const int pinSinal = 7;

//Variaveis driver RF
const int txTr = 1000;
const int pinRX = 12;

//Variáveis das mensagens
uint8_t msg2[] = {0xFA, 0x0, 0x1, 0xFB};
uint8_t msgT[] = {0xFA, 0x1, 0x1, 0xFB};
uint8_t msg[sizeof(msg2)];


void setup() {
  for (int i = 0; i < sizeof(msg); i++) {
    msg[i] = 0x00;
  }
  pinMode(pinSinal, OUTPUT); //inicia pino do sinal
  Serial.begin(9600);
  vw_set_rx_pin(pinRX); // pino de recepção
  vw_setup(txTr); // taxa de transferência
  vw_rx_start();
}

void zeraMsg() {
  for (int i = 0; i < sizeof(msg); i++) {
    msg[i] = 0x00;
  }
}

//Aciona o sinal do microcontrolador
void acionaSinal() {
  int i = 0;
  while (i < 50) {
    digitalWrite(pinSinal, HIGH);
    delay(100);
    digitalWrite(pinSinal, LOW);
    delay(100);
    i++;
  }
}

void loop() {
  uint8_t message[MAX_DATA];
  uint8_t msgLength = MAX_DATA;
  zeraMsg();
  if (vw_get_message(message, &msgLength)) {
    Serial.print("Recebido: ");
    for (int i = 0; i < msgLength; i++) {
      if (i <= 3) {
        msg[i] = message[i];

      }
      Serial.print(message[i], HEX);
      Serial.print(" ");
    }
    Serial.println();
  }
  if (msg[0] == msg2[0] && msg[1] == msg2[1] && msg[2] == msg2[2] && msg[3] == msg2[3]) {
    acionaSinal();
  } else if (msg[0] == msgT[0] && msg[1] == msgT[1] && msg[2] == msgT[2] && msg[3] == msgT[3]) {
    acionaSinal();
  }
  delay(100);
}
