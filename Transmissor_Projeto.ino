#include <VirtualWire.h>

//Variaveis Botoes
const int pinBotao1 = 3;
const int pinBotao2 = 4;
const int pinBotao3 = 5;
//int botao1 = 0;
//int botao2 = 0;
//int botao3 = 0;

//Variavel Sirene/LED
const int pinSinal = 7;

//Variáveis das mensagens
uint8_t msg0[] = {0xFA, 0x0, 0x0, 0xFB};
uint8_t msg1[] = {0xFA, 0x1, 0x0, 0xFB};
uint8_t msg2[] = {0xFA, 0x0, 0x1, 0xFB};
uint8_t msgT[] = {0xFA, 0x1, 0x1, 0xFB};

void setup() {
  //Serial.begin(9600);
  pinMode(pinSinal, OUTPUT);
  pinMode(pinBotao1, INPUT_PULLUP);
  pinMode(pinBotao2, INPUT_PULLUP);
  pinMode(pinBotao3, INPUT_PULLUP);
  digitalWrite(pinSinal, LOW);
  vw_set_tx_pin(11);
  vw_setup(1000);
}

void send (uint8_t data[], uint8_t size) {
  vw_send(data, size);
  vw_wait_tx(); // Aguarda o envio de dados
}


void loop()
{
  uint8_t data[4] = {0x00, 0x00, 0x00, 0x00};
  int botaoPressionado = 0;
  //botao1 = digitalRead(pinBotao1);
  //botao2 = digitalRead(pinBotao2);
  //botao3 = digitalRead(pinBotao3);

  if (!digitalRead(pinBotao1)) {
    while (!digitalRead(pinBotao1)) {
      delay(10);
    }
    botaoPressionado = 1;
  } else if (!digitalRead(pinBotao2)) {
    while (!digitalRead(pinBotao2)) {
      delay(10);
    }
    botaoPressionado = 2;
  } else if (!digitalRead(pinBotao3)) {
    while (!digitalRead(pinBotao3)) {
      delay(10);
    }
    botaoPressionado = 3;
  }

  switch (botaoPressionado) {
    case 1:
      for (int i = 0; i < sizeof(data); i++) {
        data[i] = msg1[i];
      }
      break;
    case 2:
      for (int i = 0; i < sizeof(data); i++) {
        data[i] = msg2[i];
      }
      break;
    case 3:
      for (int i = 0; i < sizeof(data); i++) {
        data[i] = msgT[i];
      }
      break;
      default:
      for (int i = 0; i < sizeof(data); i++) {
        data[i] = msg0[i];
      }
      break;
  }
  /*
    if (Serial.available()) {
    Serial.println(digitalRead(pinBotao1));
    Serial.println(digitalRead(pinBotao2));
    Serial.println(digitalRead(pinBotao3));
    }
  */
  send(data, sizeof(data));

  if (data[1] == 1) {
    int contador = 0;
    while (contador < 50) {
      digitalWrite(pinSinal, HIGH);
    delay(100);
    digitalWrite(pinSinal, LOW);
    delay(100);
      contador++;
    }
  } else {
    delay(10);
  }
}
