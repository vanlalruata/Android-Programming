const functions = require("firebase-functions");
const admin = require("firebase-admin");

admin.initializeApp();

const db = admin.firestore();
const fcm = admin.messaging();

// **Trigger Notification When New Data is Added**
exports.sendNotificationOnNewStudent = functions.firestore
    .document("students/{studentId}")
    .onCreate(async (snapshot, context) => {
        const student = snapshot.data();
        const message = {
            notification: {
                title: "New Student Added!",
                body: `Student ${student.name} (Age ${student.age}) was added.`,
            },
            topic: "students-updates",
        };

        return fcm.send(message);
    });

// **Trigger Notification When Data is Updated**
exports.sendNotificationOnUpdate = functions.firestore
    .document("students/{studentId}")
    .onUpdate(async (change, context) => {
        const before = change.before.data();
        const after = change.after.data();

        const message = {
            notification: {
                title: "Student Updated!",
                body: `Updated: ${before.name} → ${after.name}, Age: ${before.age} → ${after.age}`,
            },
            topic: "students-updates",
        };

        return fcm.send(message);
    });

// **Trigger Notification When Data is Deleted**
exports.sendNotificationOnDelete = functions.firestore
    .document("students/{studentId}")
    .onDelete(async (snapshot, context) => {
        const student = snapshot.data();

        const message = {
            notification: {
                title: "Student Removed!",
                body: `${student.name} was removed from the database.`,
            },
            topic: "students-updates",
        };

        return fcm.send(message);
    });
