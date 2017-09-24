package es.upm.miw.pd.state.connection;

import org.apache.logging.log4j.LogManager;

public class Connection {
    public static final int ACK = 0;
    
    private State state;

    public Connection() {
        this.state = State.CLOSED;
    }

    public State getState() {
        return this.state;
    }

    public void open() {
        if (this.state == State.CLOSED) {
            this.state = State.READY;
        } else if (this.state == State.STOPPED) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.READY) {
        } else if (this.state == State.WAITING) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else
            assert false : "estado imposible";
    }

    public void close() {
        if (this.state == State.CLOSED) {
        } else if (this.state.equals(State.STOPPED)) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.READY) {
            this.state = State.CLOSED;
        } else if (this.state == State.WAITING) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else {
            assert false : "estado imposible";
        }
    }

    public void stop() {
        if (this.state.equals(State.CLOSED)) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.STOPPED) {
        } else if (this.state == State.READY) {
            this.state = State.STOPPED;
        } else if (this.state == State.WAITING) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else {
            assert false : "estado imposible";
        }
    }

    public void start() {
        if (this.state == State.CLOSED) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.STOPPED) {
            this.state = State.READY;
        } else if (this.state == State.READY) {
        } else if (this.state == State.WAITING) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else {
            assert false : "estado imposible";
        }
    }

    public void submit(String msg) {
        if (this.state == State.CLOSED) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.STOPPED) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.READY) {
            LogManager.getLogger(this.getClass().getName()).info(msg);
            this.state = State.WAITING;
        } else if (this.state == State.WAITING) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else {
            assert false : "estado imposible";
        }
    }

    public void receive(int respuesta) {
        if (this.state == State.CLOSED) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.STOPPED) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.READY) {
            throw new UnsupportedOperationException("Acción no permitida... ");
        } else if (this.state == State.WAITING) {
            if (respuesta == ACK) {
                this.state = State.READY;
            } else {
                this.state = State.CLOSED;
            }
        } else {
            assert false : "estado imposible";
        }
    }

}